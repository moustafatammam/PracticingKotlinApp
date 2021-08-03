package com.example.practicingkotlinapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

object BindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w500" + url).override(Target.SIZE_ORIGINAL).into(view)
        }
    }
}