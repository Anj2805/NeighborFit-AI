package com.example.neighborfitai.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.neighborfitai.data.local.OnboardingPreferences
import com.example.neighborfitai.databinding.ActivityOnboardingBinding
import com.example.neighborfitai.ui.auth.RegisterActivity
import kotlinx.coroutines.launch

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardingPreferences: OnboardingPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onboardingPreferences = OnboardingPreferences(this)

        val adapter = OnboardingAdapter(this)
        binding.viewPager.adapter = adapter
    }

    fun goToNextPage() {
        binding.viewPager.currentItem += 1
    }

    fun goToPreviousPage() {
        binding.viewPager.currentItem -= 1
    }

    fun finishOnboarding() {
        lifecycleScope.launch {
            onboardingPreferences.setOnboardingCompleted(true)
            navigateToRegister()
        }
    }

    private fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }
}
