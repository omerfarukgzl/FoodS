package com.omtaem.foodapp.domain.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.omtaem.foodapp.R

@BindingAdapter("loadImageFromUrl")
fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
    imageView.load(imageUrl) {
        crossfade(600)
        error(R.drawable.ic_launcher_foreground)
    }
}