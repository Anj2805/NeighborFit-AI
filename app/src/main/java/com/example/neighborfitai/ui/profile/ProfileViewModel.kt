package com.example.neighborfitai.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neighborfitai.data.local.UserPreferenceManager
import com.example.neighborfitai.data.local.dao.SavedNeighborhoodDao
import com.example.neighborfitai.domain.model.Neighborhood
import com.example.neighborfitai.domain.repository.NeighborhoodRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: NeighborhoodRepository,
    private val savedDao: SavedNeighborhoodDao,
    private val preferenceManager: UserPreferenceManager
) : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser.asStateFlow()

    private val _savedNeighborhoods = MutableStateFlow<List<Neighborhood>>(emptyList())
    val savedNeighborhoods: StateFlow<List<Neighborhood>> = _savedNeighborhoods.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<ProfileNavigation>()
    val navigationEvent: SharedFlow<ProfileNavigation> = _navigationEvent.asSharedFlow()

    init {
        _currentUser.value = auth.currentUser
        _currentUser.value?.let { user ->
            loadSavedNeighborhoods(user.uid)
        }
    }

    private fun loadSavedNeighborhoods(userId: String) {
        viewModelScope.launch {
            val savedEntities = savedDao.getSavedNeighborhoods(userId).first()
            val neighborhoods = savedEntities.mapNotNull { saved ->
                repository.getNeighborhoodById(saved.id)
            }
            _savedNeighborhoods.value = neighborhoods
        }
    }

    fun logout() {
        viewModelScope.launch {
            // 1. Sign out from Firebase
            auth.signOut()
            
            // 2. Clear local preferences from DataStore
            preferenceManager.clearPreferences()
            
            // 3. Navigate back to login
            _navigationEvent.emit(ProfileNavigation.NavigateToLogin)
        }
    }

    sealed class ProfileNavigation {
        object NavigateToLogin : ProfileNavigation()
    }
}
