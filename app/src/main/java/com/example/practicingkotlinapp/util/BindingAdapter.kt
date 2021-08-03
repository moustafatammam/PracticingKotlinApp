package com.example.practicingkotlinapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapter {

    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(view.context).load(url).into(view)
        }
    }
}