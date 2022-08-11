package com.hua.github_app.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import com.hua.github_app.R
import com.hua.github_app.base.BaseActivity
import com.hua.github_app.databinding.ActivityHomeBinding
import com.hua.github_app.ui.fragment.RepositoryListFragment
import com.hua.github_app.ui.viewmodel.HomeViewModel

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class HomeActivity : BaseActivity() {

    companion object {
        private const val TAG = "MainActivity"

        fun jumpHome(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    private val homeVm = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.vm = homeVm
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, RepositoryListFragment())
            .commit()
        homeVm.initData(this)
    }
}