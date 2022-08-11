package com.hua.github_app.ui.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hua.github_app.R
import com.hua.github_app.base.BaseViewModel
import com.hua.github_app.ui.activity.SearchActivity
import com.hua.github_app.utils.LogUtil

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
class HomeViewModel : BaseViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    fun initData(context: Context) {
        launchMain({
            _title.value = context.getString(R.string.home)
        }, {
            LogUtil.e(TAG, "initData", it)
        })
    }

    fun onClickSearch(v: View) {
        launchMain({
            SearchActivity.jumpSearch(v.context)
        }, {
            LogUtil.e(TAG, "onClickSearch", it)
        })
    }

}