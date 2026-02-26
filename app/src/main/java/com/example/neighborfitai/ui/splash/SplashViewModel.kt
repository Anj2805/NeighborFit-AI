package com.example.neighborfitai.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neighborfitai.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<SplashNavigation>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun checkAuthState() {
        viewModelScope.launch {
            // Give a small delay for splash appearance
            delay(2000)
            val user = userRepository.getUser()
            if (user != null) {
                _navigationEvent.emit(SplashNavigation.NavigateToHome)
            } else {
                _navigationEvent.emit(SplashNavigation.NavigateToOnboarding)
            }
        }
    }

    sealed class SplashNavigation {
        object NavigateToHome : SplashNavigation()
        object NavigateToOnboarding : SplashNavigation()
    }
}
