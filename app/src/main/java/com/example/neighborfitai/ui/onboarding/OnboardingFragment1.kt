package com.example.neighborfitai.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neighborfitai.databinding.FragmentOnboarding1Binding

class OnboardingFragment1 : Fragment() {
    private var _binding: FragmentOnboarding1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboarding1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Using IDs btnNext and tvSkip as defined in fragment_onboarding_1.xml
        binding.btnNext.setOnClickListener {
            (activity as? OnboardingActivity)?.goToNextPage()
        }
        
        binding.tvSkip.setOnClickListener {
            (activity as? OnboardingActivity)?.finishOnboarding()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
