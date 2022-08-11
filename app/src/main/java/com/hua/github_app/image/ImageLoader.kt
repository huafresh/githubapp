package com.hua.github_app.image

import android.content.Context
import android.view.View
import android.widget.ImageView

/**
 * Created on 2022/8/10.
 * This class is the entrance of image loading, a simple use case is like this:
 * ImageLoader.with(context)
 *            .load(url)
 *            .into(imageView)
 * @author hua
 */
object ImageLoader {
    private val loader: ILoader = GlideLoader()

    fun with(context: Context): RequestOptions {
        return RequestOptions.newOptions(context)
    }

    fun with(view: View): RequestOptions {
        return RequestOptions.newOptions(view.context)
    }

    fun load(options: RequestOptions, imageView: ImageView) {
        loader.load(options, ImageViewTarget(imageView))
    }

    fun load(options: RequestOptions, target: ITarget) {
        loader.load(options, target)
    }
}