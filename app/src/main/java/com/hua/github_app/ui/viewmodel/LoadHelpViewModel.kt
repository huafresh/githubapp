package com.hua.github_app.ui.viewmodel

import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hua.github_app.base.BaseViewModel
import com.hua.github_app.loadview.LoadViewHelper
import com.hua.github_app.utils.LogUtil

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
class LoadHelpViewModel : BaseViewModel() {
    companion object {
        private const val TAG = "LoadHelpViewModel"
        const val VIEW_TYPE_ERROR = 1
        const val VIEW_TYPE_EMPTY = 2
    }

    private val _viewType = MutableLiveData<Int>()
    val viewType: LiveData<Int> = _viewType

    fun showError() {
        launchMain({
            _viewType.value = LoadViewHelper.LAYOUT_TYPE_ERROR
        }, {
            LogUtil.e(TAG, "showError", it)
        })
    }

    fun showEmpty() {
        launchMain({
            _viewType.value = LoadViewHelper.LAYOUT_TYPE_EMPTY
        }, {
            LogUtil.e(TAG, "showEmpty", it)
        })
    }

    fun showContent() {
        launchMain({
            _viewType.value = LoadViewHelper.LAYOUT_TYPE_ACTUAL
        }, {
            LogUtil.e(TAG, "showContent", it)
        })
    }
}