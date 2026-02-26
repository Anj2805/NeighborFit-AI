package com.example.neighborfitai.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.neighborfitai.data.local.OnboardingPreferences
import com.example.neighborfitai.databinding.ActivitySplashBinding
import com.example.neighborfitai.ui.auth.LoginActivity
import com.example.neighborfitai.ui.onboarding.OnboardingActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var onboardingPreferences: OnboardingPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onboardingPreferences = OnboardingPreferences(this)

        binding.root.setOnClickListener {
            checkOnboardingAndNavigate()
        }
    }

    private fun checkOnboardingAndNavigate() {
        lifecycleScope.launch {
            val isCompleted = onboardingPreferences.onboardingCompleted.first()
            if (isCompleted) {
                navigateToLogin()
            } else {
                navigateToOnboarding()
            }
        }
    }

    private fun navigateToOnboarding() {
        startActivity(Intent(this, OnboardingActivity::class.java))
        finish()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
