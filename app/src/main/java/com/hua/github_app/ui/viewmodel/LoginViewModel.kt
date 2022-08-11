package com.hua.github_app.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.view.View
import com.hua.github_app.http.AppRetrofit
import com.hua.github_app.base.BaseViewModel
import com.hua.github_app.AppConfig
import com.hua.github_app.ui.activity.HomeActivity
import com.hua.github_app.ui.activity.LoginActivity
import com.hua.github_app.ui.login.LoginManager
import com.hua.github_app.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


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

    fun handleNewIntent(context: Context, intent: Intent?) {
        LogUtil.i(TAG, "onNewIntent: ${intent?.data}")
        val uri = intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            val state = uri.getQueryParameter("state")
            startGetAccessToken(context, code, state)
        }
    }

    private fun startGetAccessToken(context: Context, code: String?, state: String?) {
        if (code == null || state == null) return
        launchMain({
            val oAuthToken = withContext(Dispatchers.IO) {
                AppRetrofit.getLoginService()
                    .getAccessToken(
                        AppConfig.OPENHUB_CLIENT_ID,
                        AppConfig.OPENHUB_CLIENT_SECRET,
                        code,
                        state
                    )
            }
            LogUtil.i(TAG, "get oAuthToken=$oAuthToken")
            LoginManager.token = oAuthToken.accessToken
            dismissProgressDialog()
            HomeActivity.jumpHome(context)
        }, {
            LogUtil.e(TAG, "getToken: ", it)
        })
    }

}