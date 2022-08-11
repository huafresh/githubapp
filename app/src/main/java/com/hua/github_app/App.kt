package com.hua.github_app

import android.app.Application
import com.hua.github_app.loadview.BaseLayoutProvider
import com.hua.github_app.loadview.ILayoutProvider
import com.hua.github_app.loadview.LoadViewHelper

/**
 * Created on 2022/8/9.
 *
 * @author hua
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupLoadViewHelper()
    }

    private fun setupLoadViewHelper(){
        LoadViewHelper.registerErrorLayout(object :BaseLayoutProvider(){
            override fun layoutId(): Int {
                return R.layout.load_error
            }
        })
        LoadViewHelper.registerLoadingLayout(object :BaseLayoutProvider(){
            override fun layoutId(): Int {
                return R.layout.load_empty
            }
        })
    }
}