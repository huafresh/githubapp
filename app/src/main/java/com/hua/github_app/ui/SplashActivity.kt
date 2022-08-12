package com.hua.github_app.ui

import android.os.Bundle
import com.hua.github_app.base.BaseActivity
import com.hua.github_app.ui.activity.HomeActivity
import com.hua.github_app.ui.activity.LoginActivity
import com.hua.github_app.login.LoginManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            LoginManager.autoLogin(this@SplashActivity)
            if (LoginManager.isLogin()) {
                HomeActivity.jumpHome(this@SplashActivity)
            } else {
//            SearchActivity.jumpSearch(this@SplashActivity)
                LoginActivity.jumpLogin(this@SplashActivity)
//            HomeActivity.jumpHome(this@SplashActivity)

            }
            finish()
        }
    }

}