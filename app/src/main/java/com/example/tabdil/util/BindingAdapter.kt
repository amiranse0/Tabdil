package com.example.tabdil.util

import android.annotation.SuppressLint
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.tabdil.R

@SuppressLint("ResourceAsColor")
@BindingAdapter("changePrice", requireAll = false)
fun setBackgroundCardView(view: CardView, changePrice: Double){
    var color: Int = R.color.red
    if (changePrice > 0) color = R.color.green
    view.setCardBackgroundColor(color)
}