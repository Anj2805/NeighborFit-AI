package com.example.neighborfitai.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neighborfitai.databinding.FragmentResultBinding
import com.example.neighborfitai.ui.home.NeighborhoodAdapter
import kotlinx.coroutines.launch

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    
    // In a real Hilt setup, this would be injected.
    // private val viewModel: ResultViewModel by viewModels()
    private lateinit var adapter: NeighborhoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupListeners()
        
        // Example of observing UI state
        // viewLifecycleOwner.lifecycleScope.launch {
        //     viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        //         viewModel.uiState.collect { state ->
        //             adapter.submitList(state.neighborhoods)
        //             binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        //         }
        //     }
        // }
    }

    private fun setupRecyclerView() {
        adapter = NeighborhoodAdapter { neighborhood ->
            // Handle item click - Navigate to DetailActivity
        }
        binding.itemResultContainer.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = this@ResultFragment.adapter
        }
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        
        binding.btnRefine.setOnClickListener {
            // Navigate back to PreferenceFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
