package com.hua.github_app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
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

    /**
     * observe [BaseViewModel.progressDialogConfig] to make
     * [BaseViewModel.showProgressDialog] function takes effect.
     */
    protected fun BaseViewModel.observeProgressDialog() {
        progressDialogHelper.observeProgressDialog(this)
    }

    protected lateinit var binding: binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createBinding()
        setContentView(binding.root)
        initData()
        initViews(binding)
        addObserves(binding)
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialogHelper.onDestroy()
    }

    /**
     * Create [ViewBinding] object. The general implementation is like this:
     * XXXXXBinding.inflate(layoutInflater)
     */
    protected abstract fun createBinding(): binding

    /**
     * Parse the intent parameters or initialize other data
     */
    protected abstract fun initData()

    /**
     * Initialize views
     */
    protected abstract fun initViews(binding: binding)

    /**
     * Subscribe [LiveData] here
     */
    protected abstract fun addObserves(binding: binding)
}
