package com.hua.github_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
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
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main.immediate) {
            val activity = this@SplashActivity
            LoginManager.autoLogin(activity)
            if (LoginManager.isLogin()) {
                val granted = PermissionUtil.checkStoragePermission(activity)
                if (granted) {
                    HomeActivity.show(activity)
                } else {
                    ToastUtils.show("no permission")
                    delay(2000)
                }
            } else {
                LoginActivity.show(activity)
            }
            finish()
        }
    }
}