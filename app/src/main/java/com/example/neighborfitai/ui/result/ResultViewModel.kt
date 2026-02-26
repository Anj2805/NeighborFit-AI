package com.example.neighborfitai.ui.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neighborfitai.data.local.UserPreferenceManager
import com.example.neighborfitai.domain.repository.NeighborhoodRepository
import com.example.neighborfitai.domain.usecase.CalculateMatchScoreUseCase
import com.example.neighborfitai.ui.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ResultViewModel(
    private val repository: NeighborhoodRepository,
    private val preferenceManager: UserPreferenceManager,
    private val calculateMatchScoreUseCase: CalculateMatchScoreUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        calculateResults()
    }

    fun calculateResults() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // Fetch latest preferences from DataStore
                val preferences = preferenceManager.userPreferences.first()
                
                // Fetch all neighborhoods
                val neighborhoods = repository.getNeighborhoods()
                
                // Process through matching engine
                val results = neighborhoods.map { neighborhood ->
                    val scoreResult = calculateMatchScoreUseCase(neighborhood, preferences)
                    neighborhood.copy(
                        matchPercentage = scoreResult.matchPercentage,
                        aiExplanation = scoreResult.explanation
                    )
                }.sortedByDescending { it.matchPercentage }

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    neighborhoods = results
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Analysis failed"
                )
            }
        }
    }
}
