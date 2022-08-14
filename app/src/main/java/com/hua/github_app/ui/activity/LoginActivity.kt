package com.hua.github_app.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hua.github_app.R
import com.hua.github_app.databinding.ActivityLoginBinding
import com.hua.github_app.ui.viewmodel.LoginViewModel


/**
 * Created on 2022/8/9.
 *
 * @author hua
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    companion object {
        private const val TAG = "LoginActivity"

        fun show(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    private val loginVm: LoginViewModel by viewModels()

    override fun createBinding(): ActivityLoginBinding {
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vm = loginVm
        return binding
    }

    override fun initData() {
    }

    override fun initViews(binding: ActivityLoginBinding) {
        setupActionBar()
    }

    override fun addObserves(binding: ActivityLoginBinding) {
        loginVm.observeShowingDialog()
    }

    private fun setupActionBar() {
        supportActionBar?.title = "login"
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        loginVm.handleNewIntent(this, intent)
    }

}