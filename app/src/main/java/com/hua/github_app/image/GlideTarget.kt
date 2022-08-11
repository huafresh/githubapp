package com.hua.github_app.image

import android.graphics.drawable.Drawable
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
internal class GlideTarget(private val target: ITarget) : Target<Drawable> {
    private var request: Request? = null

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }

    override fun onLoadStarted(placeholder: Drawable?) {
        target.onLoadStarted(placeholder)
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        target.onLoadFailed(errorDrawable)
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        target.onResourceReady(resource)
    }

    override fun onLoadCleared(placeholder: Drawable?) {
        target.onLoadCleared(placeholder)
    }

    override fun getSize(cb: SizeReadyCallback) {
        cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
    }

    override fun removeCallback(cb: SizeReadyCallback) {
    }

    override fun setRequest(request: Request?) {
        this.request = request
    }

    override fun getRequest(): Request? {
        return request
    }
}