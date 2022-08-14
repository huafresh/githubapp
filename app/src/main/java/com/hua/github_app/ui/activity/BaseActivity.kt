package com.hua.github_app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import com.hua.github_app.ui.viewmodel.BaseViewModel
import com.hua.github_app.ui.dialog.ProgressDialogHelper

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
abstract class BaseActivity : AppCompatActivity() {

    private val progressDialogHelper by lazy {
        ProgressDialogHelper(this)
    }

    protected fun BaseViewModel.observeShowingDialog() {
        progressDialogHelper.observeShowingDialog(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialogHelper.onDestroy()
    }
}