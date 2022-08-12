package com.hua.github_app.ui.viewmodel

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.hua.github_app.base.BaseViewModel
import com.hua.github_app.AppConfig
import com.hua.github_app.R
import com.hua.github_app.ui.activity.HomeActivity
import com.hua.github_app.login.LoginManager
import com.hua.github_app.utils.LogUtil
import com.hua.github_app.utils.PermissionUtil
import com.permissionx.guolindev.PermissionX
import com.permissionx.guolindev.callback.RequestCallback
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


/**
 * Created on 2022/8/9.
 *
 * @author hua
 */
class LoginViewModel : BaseViewModel() {
    companion object {
        private const val TAG = "LoginViewModel"
    }

    fun onClickLoginWithBrowser(v: View) {
        launchMain({
            val granted = PermissionUtil.checkStoragePermission(v.context as FragmentActivity)
            if (!granted) {
                return@launchMain
            }

            /**
             * Jump to browser authorization. After the authorization is successful,
             * it will be called back through scheme: openhub://login
             */
            val uri: Uri = Uri.parse(getOAuth2Url())
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            v.context.startActivity(intent)
            showProgressDialog()
        }, {
            LogUtil.e(TAG, "onClickLoginWithBrowser", it)
        })
    }

    private fun getOAuth2Url(): String {
        val randomState = UUID.randomUUID().toString()
        return AppConfig.OAUTH2_URL.toString() +
                "?client_id=" + AppConfig.OPENHUB_CLIENT_ID +
                "&scope=" + AppConfig.OAUTH2_SCOPE +
                "&state=" + randomState
    }

    fun handleNewIntent(activity: Activity, intent: Intent?) {
        launchMain({
            LogUtil.i(TAG, "onNewIntent: ${intent?.data}")
            val uri = intent?.data
            if (uri != null) {
                val code = uri.getQueryParameter("code")
                val state = uri.getQueryParameter("state")
                if (code == null || state == null) {
                    LogUtil.e(TAG, "login failed: code or state is empty")
                    activity.toastLoginFailed()
                    dismissProgressDialog()
                    return@launchMain
                }
                val ret = LoginManager.handleGetCodeState(activity, code, state)
                if (!ret) {
                    LogUtil.e(TAG, "login failed: handle code state failed")
                    activity.toastLoginFailed()
                    dismissProgressDialog()
                    return@launchMain
                }
                dismissProgressDialog()
                HomeActivity.jumpHome(activity)
                activity.finish()
            }
        }, {
            LogUtil.e(TAG, "handleNewIntent", it)
            dismissProgressDialog()
            activity.toastLoginFailed()
        })
    }

    private fun Context.toastLoginFailed() {
        Toast.makeText(
            this, this.getString(R.string.login_failed),
            Toast.LENGTH_SHORT
        ).show()
    }

}