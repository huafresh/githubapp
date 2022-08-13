package com.hua.github_app.ui

import android.os.Bundle
import android.widget.Toast
import com.hua.github_app.base.BaseActivity
import com.hua.github_app.ui.activity.HomeActivity
import com.hua.github_app.ui.activity.LoginActivity
import com.hua.github_app.login.LoginManager
import com.hua.github_app.utils.PermissionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main.immediate) {
            LoginManager.autoLogin(this@SplashActivity)
            if (LoginManager.isLogin()) {
                val b = PermissionUtil.checkStoragePermission(this@SplashActivity)
                if (b) {
                    HomeActivity.show(this@SplashActivity)
                } else {
                    Toast.makeText(
                        this@SplashActivity,
                        "no permission", Toast.LENGTH_SHORT
                    ).show()
                    delay(2000)
                    finish()
                }
            } else {
                LoginActivity.jumpLogin(this@SplashActivity)
            }
            finish()
        }
    }

}