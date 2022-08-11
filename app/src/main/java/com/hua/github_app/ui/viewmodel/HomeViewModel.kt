package com.hua.github_app.ui.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.hua.github_app.R
import com.hua.github_app.base.BaseViewModel
import com.hua.github_app.http.AppRetrofit
import com.hua.github_app.ui.activity.SearchActivity
import com.hua.github_app.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

    private val _avatarUrl = MutableLiveData<String>()
    val avatarUrl: LiveData<String> = _avatarUrl

    fun initData(context: Context) {
        launchMain({
            _title.value = context.getString(R.string.home_title)
            val url = withContext(Dispatchers.IO) {
                AppRetrofit.getUserService().getPersonInfo().avatarUrl ?: ""
            }
            _avatarUrl.value = url
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

    fun onClickAvatar(v: View) {
        launchMain({
            Snackbar.make(
                v,
                v.context.getString(R.string.profile_not_finish),
                Snackbar.LENGTH_SHORT
            ).show()
        }, {
            LogUtil.e(TAG, "onClickAvatar", it)
        })
    }
}