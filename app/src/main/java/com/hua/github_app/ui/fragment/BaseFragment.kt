package com.hua.github_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hua.github_app.ui.viewmodel.BaseViewModel
import com.hua.github_app.ui.dialog.ProgressDialogHelper

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
abstract class BaseFragment<binding : ViewBinding> : Fragment() {

    private val progressDialogHelper by lazy {
        activity?.let { ProgressDialogHelper(it) }
    }

    protected fun BaseViewModel.observeShowingDialog() {
        progressDialogHelper?.observeShowingDialog(this)
    }

    protected var binding: binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createBinding()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
        binding?.let {
            initViews(it)
            addObserves(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialogHelper?.onDestroy()
    }

    protected abstract fun createBinding(): binding
    protected abstract fun initData()
    protected abstract fun initViews(binding: binding)
    protected abstract fun addObserves(binding: binding)
}