package com.example.tabdil.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabdil.databinding.NameFavoriteCardViewBinding

class FavoriteAdapter: ListAdapter<String, FavoriteAdapter.FavoriteViewHolder>(
    FavoriteDiffCallback()
) {

    class FavoriteViewHolder(
        val binding: NameFavoriteCardViewBinding
    ): RecyclerView.ViewHolder(binding.root){
        val context = binding.root.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = NameFavoriteCardViewBinding.inflate(inflater)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.binding.nameCurrencyFavoriteTv.text = getItem(position)
    }

}

class FavoriteDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}