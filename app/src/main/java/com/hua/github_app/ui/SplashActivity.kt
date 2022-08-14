package com.hua.github_app.ui

import android.os.Bundle
import com.hjq.toast.ToastUtils
import com.hua.github_app.ui.activity.BaseActivity
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
                val granted = PermissionUtil.checkStoragePermission(this@SplashActivity)
                if (granted) {
                    HomeActivity.show(this@SplashActivity)
                } else {
                    ToastUtils.show("no permission")
                    delay(2000)
                    finish()
                }
            } else {
                LoginActivity.show(this@SplashActivity)
            }
            finish()
        }
    }

}