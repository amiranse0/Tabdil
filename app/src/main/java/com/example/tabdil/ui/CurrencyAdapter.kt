package com.example.tabdil.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabdil.data.model.local.LocalCurrency
import com.example.tabdil.databinding.CurrencyCardViewBinding


class CurrencyAdapter : ListAdapter<LocalCurrency, CurrencyAdapter.CurrencyViewHolder>(
    CurrencyDiffCallback()
) {

    private var favoriteClickListener: OnFavoriteClickListener? = null
    private var pinClickListener: OnPinClickListener? = null

    fun setFavoriteClickListener(listener: OnFavoriteClickListener?) {
        favoriteClickListener = listener
    }

    fun setPinClickListener(listener: OnPinClickListener?) {
        pinClickListener = listener
    }

    class CurrencyViewHolder(
        val binding: CurrencyCardViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val context: Context = binding.root.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CurrencyCardViewBinding.inflate(inflater)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.favoriteIcon.setOnClickListener {
            if (item.isFavorite) Toast.makeText(
                holder.context,
                "${item.name} removed from favorites.",
                Toast.LENGTH_SHORT
            ).show()
            else Toast.makeText(
                holder.context,
                "${item.name} added to favorites.",
                Toast.LENGTH_SHORT
            ).show()
            item.isFavorite = item.isFavorite == false
            holder.binding.currency = item
            favoriteClickListener!!.onFavoriteClick(item)
        }

        holder.binding.pinIcon.setOnClickListener {
            if (item.isPin) Toast.makeText(
                holder.context,
                "${item.name} unpinned.",
                Toast.LENGTH_SHORT
            ).show()
            else Toast.makeText(holder.context, "${item.name} pinned.", Toast.LENGTH_SHORT).show()
            item.isPin = item.isPin == false
            holder.binding.currency = item
            pinClickListener!!.onPinClick(item)
        }
        holder.binding.currency = item
    }

}

interface OnFavoriteClickListener {
    fun onFavoriteClick(item: LocalCurrency)
}

interface OnPinClickListener {
    fun onPinClick(item: LocalCurrency)
}

class CurrencyDiffCallback : DiffUtil.ItemCallback<LocalCurrency>() {
    override fun areItemsTheSame(oldItem: LocalCurrency, newItem: LocalCurrency): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocalCurrency, newItem: LocalCurrency): Boolean {
        return oldItem.name == newItem.name && oldItem.persianName == newItem.persianName
    }

}