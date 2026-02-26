package com.example.neighborfitai.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.neighborfitai.databinding.ItemResultNeighborhoodBinding
import com.example.neighborfitai.domain.model.Neighborhood

class NeighborhoodAdapter(
    private val onDetailsClick: (Neighborhood) -> Unit
) : ListAdapter<Neighborhood, NeighborhoodAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private val binding: ItemResultNeighborhoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(neighborhood: Neighborhood, onDetailsClick: (Neighborhood) -> Unit) {
            binding.tvNeighborhoodName.text = neighborhood.name
            binding.tvMatchPercentage.text = "${neighborhood.matchPercentage}%"
            binding.tvDescription.text = neighborhood.aiExplanation
            binding.btnViewDetails.setOnClickListener { onDetailsClick(neighborhood) }
            
            // In a real app, load imageUrl using Glide/Coil
            // binding.ivNeighborhood.setImageResource(...) 
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemResultNeighborhoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onDetailsClick)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Neighborhood>() {
        override fun areItemsTheSame(oldItem: Neighborhood, newItem: Neighborhood): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Neighborhood, newItem: Neighborhood): Boolean {
            return oldItem == newItem
        }
    }
}
