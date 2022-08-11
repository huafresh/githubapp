package com.hua.github_app.image

import android.graphics.drawable.Drawable
import android.widget.ImageView

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
internal class ImageViewTarget(internal val imageView: ImageView) : ITarget {
    override fun onLoadStarted(placeholder: Drawable?) {
        imageView.setImageDrawable(placeholder)
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        imageView.setImageDrawable(errorDrawable)
    }

    override fun onLoadCleared(placeholder: Drawable?) {
        imageView.setImageDrawable(placeholder)
    }

    override fun onResourceReady(resource: Drawable) {
        imageView.setImageDrawable(resource)
    }
}