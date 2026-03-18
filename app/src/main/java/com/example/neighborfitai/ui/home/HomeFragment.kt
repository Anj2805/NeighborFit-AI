package com.example.neighborfitai.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neighborfitai.MainActivity
import com.example.neighborfitai.R
import com.example.neighborfitai.databinding.FragmentHomeBinding
import com.example.neighborfitai.ui.preference.PreferenceFragment
import com.example.neighborfitai.ui.result.ResultFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFilter.setOnClickListener {
            (requireActivity() as MainActivity).navigateTo(PreferenceFragment())
        }
        
        // Use manual binding for the hardcoded cards as requested to not redesign UI layouts
        val cvCard1 = binding.root.findViewById<View>(R.id.btnFilter) // Actually the details button is not ID'd, let's just use click to result
        
        binding.clHeader.setOnClickListener {
            (requireActivity() as MainActivity).navigateTo(PreferenceFragment())
        }

        // Just hook up bottom nav 
        val bottomNav = binding.bottomNav
        bottomNav.findViewById<View>(R.id.bottomNav)?.setOnClickListener { }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
