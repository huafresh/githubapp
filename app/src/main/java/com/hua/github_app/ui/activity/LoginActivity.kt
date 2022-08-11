package com.hua.github_app.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hua.github_app.R
import com.hua.github_app.base.BaseActivity
import com.hua.github_app.databinding.ActivityLoginBinding
import com.hua.github_app.ui.viewmodel.LoginViewModel
import com.hua.github_app.utils.LogUtil


/**
 * Created on 2022/8/9.
 *
 * @author hua
 */
class LoginActivity : BaseActivity() {

    companion object {
        private const val TAG = "LoginActivity"

        fun jumpLogin(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    //    private val loginVm: LoginViewModel by viewModels()
    private val loginVm: LoginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(
            this, R.layout.activity_login
        )
        binding.lifecycleOwner = this
        binding.vm = loginVm
        observeShowingDialog(loginVm)
        supportActionBar?.title = "login"
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtil.i(TAG, "onNewIntent: ${intent?.data}")
        val uri = intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            val state = uri.getQueryParameter("state")
            loginVm.startGetAccessToken(this, code, state)
        }
    }

}