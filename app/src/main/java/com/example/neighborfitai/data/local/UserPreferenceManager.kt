package com.example.neighborfitai.data.local

import android.content.Context
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.neighborfitai.domain.model.Preference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferenceManager(private val context: Context) {

    companion object {
        val MAX_BUDGET = doublePreferencesKey("max_budget")
        val PREFERRED_CITY = stringPreferencesKey("preferred_city")
        val SAFETY_WEIGHT = intPreferencesKey("safety_weight")
        val COMMUTE_WEIGHT = intPreferencesKey("commute_weight")
        val SCHOOL_WEIGHT = intPreferencesKey("school_weight")
        val NIGHTLIFE_WEIGHT = intPreferencesKey("nightlife_weight")
    }

    val userPreferences: Flow<Preference> = context.dataStore.data.map { preferences ->
        Preference(
            maxBudget = preferences[MAX_BUDGET] ?: 25000.0,
            preferredCity = preferences[PREFERRED_CITY] ?: "Bangalore",
            safetyWeight = preferences[SAFETY_WEIGHT] ?: 5,
            commuteWeight = preferences[COMMUTE_WEIGHT] ?: 5,
            schoolWeight = preferences[SCHOOL_WEIGHT] ?: 5,
            nightlifeWeight = preferences[NIGHTLIFE_WEIGHT] ?: 5
        )
    }

    suspend fun savePreferences(preference: Preference) {
        context.dataStore.edit { preferences ->
            preferences[MAX_BUDGET] = preference.maxBudget
            preferences[PREFERRED_CITY] = preference.preferredCity
            preferences[SAFETY_WEIGHT] = preference.safetyWeight
            preferences[COMMUTE_WEIGHT] = preference.commuteWeight
            preferences[SCHOOL_WEIGHT] = preference.schoolWeight
            preferences[NIGHTLIFE_WEIGHT] = preference.nightlifeWeight
        }
    }

    suspend fun clearPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
