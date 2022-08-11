package com.hua.github_app.image

import android.graphics.drawable.Drawable
import com.bumptech.glide.manager.LifecycleListener

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */

interface ITarget {
    fun onLoadStarted(placeholder: Drawable?)

    fun onLoadFailed(errorDrawable: Drawable?)

    fun onLoadCleared(placeholder: Drawable?)

    fun onResourceReady(resource: Drawable)
}