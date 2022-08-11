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



    fun initData(context: Context) {
        launchMain({
        }, {
            LogUtil.e(TAG, "initData", it)
        })
    }

    fun onQueryTextSubmit(context: Context, query: String?) {
        launchMain({
            Toast.makeText(context, "hell", Toast.LENGTH_SHORT).show()
        }, {
            LogUtil.e(TAG, "onQueryTextSubmit", it)
        })
    }

    fun onQueryTextChange(context: Context, newText: String?) {
        launchMain({
        }, {
            LogUtil.e(TAG, "onQueryTextChange", it)
        })
    }
}