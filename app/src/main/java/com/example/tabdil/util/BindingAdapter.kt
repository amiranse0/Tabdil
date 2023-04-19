package com.example.tabdil.util

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.tabdil.R

@BindingAdapter("changePrice", requireAll = false)
fun setBackgroundCardView(view: CardView, changePrice: Double){
    if (changePrice >= 0) view.setCardBackgroundColor(view.context.getColor(R.color.green))
    else view.setCardBackgroundColor(view.context.getColor(R.color.red))
}

@BindingAdapter("clickIconForFavorite", requireAll = false)
fun clickOnIcons(view: ImageView, boolValue: Boolean){
    view.setOnClickListener {

    }
}

@BindingAdapter("srcChooserForFavorite", requireAll = false)
fun srcChooser(view: ImageView, boolValue: Boolean){
    if(boolValue){

    }
}