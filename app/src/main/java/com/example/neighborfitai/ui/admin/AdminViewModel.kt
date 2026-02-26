package com.example.neighborfitai.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neighborfitai.domain.model.Neighborhood
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AdminViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val neighborhoodCollection = db.collection("neighborhoods")

    private val _adminState = MutableStateFlow<AdminUiState>(AdminState.Idle)
    val adminState: StateFlow<AdminUiState> = _adminState.asStateFlow()

    fun addNeighborhood(neighborhood: Neighborhood) {
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            try {
                neighborhoodCollection.document(neighborhood.id).set(neighborhood).await()
                _adminState.value = AdminState.Success("Neighborhood added successfully")
            } catch (e: Exception) {
                _adminState.value = AdminState.Error(e.message ?: "Failed to add neighborhood")
            }
        }
    }

    fun updateNeighborhood(neighborhood: Neighborhood) {
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            try {
                neighborhoodCollection.document(neighborhood.id).set(neighborhood).await()
                _adminState.value = AdminState.Success("Neighborhood updated successfully")
            } catch (e: Exception) {
                _adminState.value = AdminState.Error(e.message ?: "Failed to update neighborhood")
            }
        }
    }

    fun deleteNeighborhood(neighborhoodId: String) {
        viewModelScope.launch {
            _adminState.value = AdminState.Loading
            try {
                neighborhoodCollection.document(neighborhoodId).delete().await()
                _adminState.value = AdminState.Success("Neighborhood deleted successfully")
            } catch (e: Exception) {
                _adminState.value = AdminState.Error(e.message ?: "Failed to delete neighborhood")
            }
        }
    }

    sealed class AdminState {
        object Idle : AdminUiState
        object Loading : AdminUiState
        data class Success(val message: String) : AdminUiState
        data class Error(val message: String) : AdminUiState
    }
}

interface AdminUiState
