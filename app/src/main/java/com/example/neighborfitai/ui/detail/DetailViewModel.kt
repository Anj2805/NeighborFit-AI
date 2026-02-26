package com.example.neighborfitai.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neighborfitai.data.local.UserPreferenceManager
import com.example.neighborfitai.domain.model.Neighborhood
import com.example.neighborfitai.domain.repository.NeighborhoodRepository
import com.example.neighborfitai.domain.usecase.CalculateMatchScoreUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: NeighborhoodRepository,
    private val preferenceManager: UserPreferenceManager,
    private val calculateMatchScoreUseCase: CalculateMatchScoreUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadNeighborhoodDetails(neighborhoodId: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            try {
                val neighborhood = repository.getNeighborhoodById(neighborhoodId)
                if (neighborhood != null) {
                    val preferences = preferenceManager.userPreferences.first()
                    val aiScore = calculateMatchScoreUseCase(neighborhood, preferences)
                    
                    val detailedNeighborhood = neighborhood.copy(
                        matchPercentage = aiScore.matchPercentage,
                        aiExplanation = aiScore.explanation
                    )
                    
                    _uiState.value = DetailUiState.Success(detailedNeighborhood)
                } else {
                    _uiState.value = DetailUiState.Error("Neighborhood not found")
                }
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e.message ?: "Failed to load details")
            }
        }
    }

    sealed class DetailUiState {
        object Loading : DetailUiState()
        data class Success(val neighborhood: Neighborhood) : DetailUiState()
        data class Error(val message: String) : DetailUiState()
    }
}
