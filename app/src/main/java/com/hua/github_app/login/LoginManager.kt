package com.hua.github_app.login

import android.content.Context
import com.hua.github_app.AppConfig
import com.hua.github_app.db.AppDatabase
import com.hua.github_app.db.user.LocalUser
import com.hua.github_app.http.AppRetrofit
import com.hua.github_app.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
object LoginManager {
    private const val TAG = "LoginManager"

    private const val DB_ID = 1

    var accessToken: String? = null
    private var localUser: LocalUser? = null

    fun isLogin(): Boolean {
        return localUser != null
    }

    fun getUser(): LocalUser? {
        return localUser
    }

    suspend fun handleGetCodeState(
        context: Context,
        code: String, state: String
    ): Boolean {
        return withContext(Dispatchers.IO) {
            val oAuthToken = AppRetrofit.getLoginService()
                .getAccessToken(
                    AppConfig.OPENHUB_CLIENT_ID,
                    AppConfig.OPENHUB_CLIENT_SECRET,
                    code,
                    state
                )
            accessToken = oAuthToken.accessToken
            val user = AppRetrofit.getUserService().getPersonInfo()
            val dbUser = LocalUser(
                DB_ID, accessToken,
                user.name, user.avatarUrl
            )
            val ret = AppDatabase.get(context).userDao().insert(dbUser)
            localUser = dbUser
            ret != -1L
        }
    }

    suspend fun autoLogin(context: Context) {
        localUser = withContext(Dispatchers.IO) {
            AppDatabase.get(context).userDao().getUser(DB_ID)
        }
        accessToken = localUser?.token
        LogUtil.i(TAG, "autoLogin: user=$localUser")
    }

    suspend fun logout(context: Context): Boolean {
        val result = withContext(Dispatchers.IO) {
            AppDatabase.get(context).userDao().delete(LocalUser(DB_ID))
        }
        LogUtil.i(TAG, "logout: result=$result")
        localUser = null
        return true
    }
}