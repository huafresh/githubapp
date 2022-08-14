package com.hua.github_app.ui.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.hua.github_app.R
import com.hua.github_app.http.AppRetrofit
import com.hua.github_app.entity.User
import com.hua.github_app.login.LoginManager
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

    val openDrawer: OneshotLiveData<Boolean> = OneshotLiveData<Boolean>()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun initData(context: Context) {
        launchMain({
            _title.value = context.getString(R.string.home_title)
            val user = LoginManager.getUser()
            _avatarUrl.value = user?.avatarUrl
            _user.value = withContext(Dispatchers.IO) {
                AppRetrofit.getUserService().getPersonInfo()
            }
        }, {
            LogUtil.e(TAG, "initData", it)
        })
    }

    fun onClickSearch(v: View) {
        launchMain({
            SearchActivity.show(v.context)
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

    fun onClickDrawerEntrance(v: View) {
        launchMain({
            openDrawer.setValue(true)
        }, {
            LogUtil.e(TAG, "onClickDrawerEntrance", it)
        })
    }

    fun onclickLogout() {
        launchMain({

        }, {
            LogUtil.e(TAG, "onclickLogout", it)
        })
    }
}