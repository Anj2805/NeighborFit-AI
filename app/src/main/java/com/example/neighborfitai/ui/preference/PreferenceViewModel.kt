package com.example.neighborfitai.ui.preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neighborfitai.data.local.UserPreferenceManager
import com.example.neighborfitai.domain.model.Preference
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PreferenceViewModel(private val preferenceManager: UserPreferenceManager) : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<PreferenceNavigation>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun saveUserPreferences(
        budget: Double,
        safety: Int,
        commute: Int,
        schools: Int,
        nightlife: Int,
        city: String
    ) {
        viewModelScope.launch {
            val preference = Preference(
                maxBudget = budget,
                safetyWeight = safety,
                commuteWeight = commute,
                schoolWeight = schools,
                nightlifeWeight = nightlife
            )
            preferenceManager.savePreferences(preference, city)
            _navigationEvent.emit(PreferenceNavigation.NavigateToHome)
        }
    }

    sealed class PreferenceNavigation {
        object NavigateToHome : PreferenceNavigation()
    }
}
