package com.hua.github_app.image

import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
internal class GlideLoader : ILoader {
    override fun load(options: RequestOptions, target: ITarget) {
        var requestBuilder = Glide.with(options.context).load(options.url)

        val errorResId = options.errorResId
        if (errorResId != null) {
            requestBuilder = requestBuilder.error(errorResId)
        }

        val errorDrawable = options.errorDrawable
        if (errorDrawable != null) {
            requestBuilder = requestBuilder.error(errorDrawable)
        }

        val placeholderDrawable = options.placeholderDrawable
        if (placeholderDrawable != null) {
            requestBuilder = requestBuilder.error(placeholderDrawable)
        }

        // ... add more options in the future

        if (target is ImageViewTarget) {
            requestBuilder.into(target.imageView)
        } else {
            requestBuilder.into(GlideTarget(target))
        }
    }

}