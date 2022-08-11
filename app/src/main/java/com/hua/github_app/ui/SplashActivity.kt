package com.hua.github_app.ui

import android.os.Bundle
import com.hua.github_app.base.BaseActivity
import com.hua.github_app.ui.activity.HomeActivity
import com.hua.github_app.ui.activity.LoginActivity
import com.hua.github_app.login.LoginManager

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (LoginManager.token != null) {
            HomeActivity.jumpHome(this)
        } else {
//            SearchActivity.jumpSearch(this)
            LoginActivity.jumpLogin(this)
//            HomeActivity.jumpHome(this)

        }

        finish()
    }

}