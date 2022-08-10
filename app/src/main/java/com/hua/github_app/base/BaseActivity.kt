package com.hua.github_app.base

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import com.hua.github_app.R

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
abstract class BaseActivity : AppCompatActivity() {

    private val progressDialogHelper by lazy {
        ProgressDialogHelper(this)
    }

    protected fun observeShowingDialog(vm: BaseViewModel) {
        progressDialogHelper.observeShowingDialog(vm)
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialogHelper.onDestroy()
    }
}