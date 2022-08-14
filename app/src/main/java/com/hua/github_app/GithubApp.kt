package com.hua.github_app

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.hjq.toast.ToastUtils
import com.hua.github_app.image.ImageLoader
import com.hua.github_app.lib.loadview.BaseLayoutProvider
import com.hua.github_app.lib.loadview.LoadViewHelper
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerUtils

/**
 * Created on 2022/8/9.
 *
 * @author hua
 */
class GithubApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        initLoadViewHelper()
        initMaterialDrawerWidget()
        ToastUtils.init(this)
    }

    private fun initMaterialDrawerWidget() {
        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
            override fun placeholder(ctx: Context, tag: String?): Drawable {
                return AppCompatResources.getDrawable(ctx, R.drawable.logo_big)
                    ?: DrawerUtils.getPlaceHolder(ctx)
            }

            override fun set(
                imageView: ImageView, uri: Uri,
                placeholder: Drawable, tag: String?
            ) {
                ImageLoader.with(imageView)
                    .load(uri.toString())
                    .placeholder(placeholder)
                    .into(imageView)
            }
        })
    }

    private fun initLoadViewHelper() {
        LoadViewHelper.registerErrorLayout(object : BaseLayoutProvider() {
            override fun layoutId(): Int {
                return R.layout.load_error
            }
        })
        LoadViewHelper.registerEmptyLayout(object : BaseLayoutProvider() {
            override fun layoutId(): Int {
                return R.layout.load_empty
            }
        })
    }
}