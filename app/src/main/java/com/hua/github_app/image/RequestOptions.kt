package com.hua.github_app.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView

/**
 * Created on 2022/8/10.
 * This class contains all the params needed to perform an image load
 *
 * @author hua
 */
class RequestOptions private constructor(val context: Context) {
    companion object {
        fun newOptions(context: Context): RequestOptions {
            return RequestOptions(context)
        }
    }

    var url: String? = null
        private set
    var errorResId: Int? = null
        private set
    var errorDrawable: Drawable? = null
        private set
    var placeholderDrawable: Drawable? = null
        private set

    fun load(url: String?): RequestOptions {
        this.url = url
        return this
    }

    fun error(resId: Int): RequestOptions {
        this.errorResId = resId
        return this
    }

    fun error(errorDrawable: Drawable?): RequestOptions {
        this.errorDrawable = errorDrawable
        return this
    }

    fun placeholder(placeholder: Drawable?): RequestOptions {
        this.placeholderDrawable = placeholder
        return this
    }

    fun into(imageView: ImageView) {
        ImageLoader.load(this, imageView)
    }

    fun into(target: ITarget) {
        ImageLoader.load(this, target)
    }

}