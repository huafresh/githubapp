package com.hua.github_app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.hua.github_app.loadview.BaseLayoutProvider
import com.hua.github_app.loadview.LoadViewHelper

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
        setupLoadViewHelper()
    }

    private fun setupLoadViewHelper() {
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