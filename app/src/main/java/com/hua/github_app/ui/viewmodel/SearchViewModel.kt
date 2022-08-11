package com.hua.github_app.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hua.github_app.R
import com.hua.github_app.base.BaseViewModel
import com.hua.github_app.utils.LogUtil

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
class SearchViewModel : BaseViewModel() {

    companion object {
        private const val TAG = "SearchViewModel"
    }

    private val _inputMode = MutableLiveData<Boolean>()
    val inputMode: LiveData<Boolean> = _inputMode

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    fun initData(context: Context) {
        launchMain({
            _title.value = ""
            _inputMode.value = true
        }, {
            LogUtil.e(TAG, "initData", it)
        })
    }

    fun onQueryTextSubmit(context: Context, query: String?) {
        launchMain({
            LogUtil.i(TAG, "onQueryTextSubmit: ")
            _title.value = query
            _inputMode.postValue(false)
        }, {
            LogUtil.e(TAG, "onQueryTextSubmit", it)
        })
    }
}