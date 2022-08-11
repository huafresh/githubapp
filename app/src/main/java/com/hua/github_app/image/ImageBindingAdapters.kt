package com.hua.github_app.image

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
object ImageBindingAdapters {

    @BindingAdapter("imageUrl", "error", "placeholder")
    @JvmStatic
    fun loadImage(
        view: ImageView, url: String,
        error: Drawable?, placeholder: Drawable?
    ) {
        ImageLoader.with(view)
            .load(url)
            .placeholder(placeholder)
            .error(error)
            .into(view)
    }

}