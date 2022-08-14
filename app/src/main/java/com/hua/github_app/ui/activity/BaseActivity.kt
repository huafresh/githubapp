package com.hua.github_app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.hua.github_app.ui.viewmodel.BaseViewModel
import com.hua.github_app.ui.dialog.ProgressDialogHelper

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
abstract class BaseActivity<binding : ViewBinding> : AppCompatActivity() {

    private val progressDialogHelper by lazy {
        ProgressDialogHelper(this)
    }

    protected fun BaseViewModel.observeShowingDialog() {
        progressDialogHelper.observeShowingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = createBinding()
        setContentView(binding.root)
        initData()
        initViews(binding)
        addObserves(binding)
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialogHelper.onDestroy()
    }

    protected abstract fun createBinding(): binding
    protected abstract fun initData()
    protected abstract fun initViews(binding: binding)
    protected abstract fun addObserves(binding: binding)
}
