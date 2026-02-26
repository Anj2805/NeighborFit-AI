package com.example.neighborfitai.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val ONBOARDING_PREFS_NAME = "onboarding_prefs"

private val ONBOARDING_COMPLETED_KEY = booleanPreferencesKey("onboarding_completed")

val Context.onboardingDataStore by preferencesDataStore(
    name = ONBOARDING_PREFS_NAME
)

class OnboardingPreferences(private val context: Context) {

    val onboardingCompleted: Flow<Boolean> =
        context.onboardingDataStore.data.map { prefs: Preferences ->
            prefs[ONBOARDING_COMPLETED_KEY] ?: false
        }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.onboardingDataStore.edit { prefs ->
            prefs[ONBOARDING_COMPLETED_KEY] = completed
        }
    }
}

