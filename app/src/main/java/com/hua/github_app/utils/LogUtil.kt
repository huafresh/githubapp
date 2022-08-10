package com.hua.github_app.utils

import android.util.Log
import com.hua.github_app.BuildConfig

/**
 * Created on 2022/8/9.
 *
 * @author hua
 */
object LogUtil {

    private val DEBUG = BuildConfig.DEBUG

    @JvmStatic
    fun i(tag: String, msg: String) {
        if (DEBUG) {
            Log.i(tag, msg)
        }
    }


    @JvmStatic
    fun e(tag: String, msg: String) {
        if (DEBUG) {
            Log.e(tag, msg)
        }
    }

    @JvmStatic
    fun e(tag: String, msg: String, throwable: Throwable) {
        if (DEBUG) {
            Log.e(tag, msg, throwable)
        }
    }

}