package com.example.tabdil.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.tabdil.data.model.local.LocalCurrency
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabdil.databinding.CurrencyCardViewBinding

class CurrencyAdapter: ListAdapter<LocalCurrency, CurrencyAdapter.CurrencyViewHolder>(
    CurrencyDiffCallback()
) {

    class CurrencyViewHolder(
        val binding: CurrencyCardViewBinding
    ): RecyclerView.ViewHolder(binding.root){
        val context: Context = binding.root.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CurrencyCardViewBinding.inflate(inflater)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.currency = item
    }
}

class CurrencyDiffCallback : DiffUtil.ItemCallback<LocalCurrency>(){
    override fun areItemsTheSame(oldItem: LocalCurrency, newItem: LocalCurrency): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocalCurrency, newItem: LocalCurrency): Boolean {
        return oldItem.name == newItem.name && oldItem.persianName == newItem.persianName
    }

}