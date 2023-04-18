package com.example.tabdil.util

import android.annotation.SuppressLint
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.tabdil.R

@BindingAdapter("changePrice", requireAll = false)
fun setBackgroundCardView(view: CardView, changePrice: Double){
    if (changePrice >= 0) view.setCardBackgroundColor(view.context.getColor(R.color.green))
    else view.setCardBackgroundColor(view.context.getColor(R.color.red))
}