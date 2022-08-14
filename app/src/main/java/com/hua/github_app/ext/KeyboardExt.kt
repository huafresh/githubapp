package com.hua.github_app.ext

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.hua.github_app.utils.LogUtil

/**
 * Created on 2022/8/14.
 *
 * @author hua
 */

fun Activity.hideSoftKeyboard() {
    try {
        val inputMethodManager: InputMethodManager? =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    } catch (e: Exception) {
        LogUtil.e("KeyboardExt", "hideSoftKeyboard: ", e)
    }
}