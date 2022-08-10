package com.hua.github_app.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hua.github_app.R

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
abstract class BaseFragment : Fragment() {
    protected abstract fun layoutId(): Int

    private val progressDialogHelper by lazy {
        ProgressDialogHelper(activity!!)
    }

    protected fun observeShowingDialog(vm: BaseViewModel) {
        progressDialogHelper.observeShowingDialog(vm)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialogHelper.onDestroy()
    }
}