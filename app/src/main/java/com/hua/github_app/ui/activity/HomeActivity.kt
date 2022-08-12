package com.hua.github_app.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil.setContentView
import com.hua.github_app.R
import com.hua.github_app.base.BaseActivity
import com.hua.github_app.databinding.ActivityHomeBinding
import com.hua.github_app.ui.fragment.RepoListFragment
import com.hua.github_app.ui.viewmodel.BaseRepoListViewModel
import com.hua.github_app.ui.viewmodel.HomeViewModel
import com.hua.github_app.ui.viewmodel.MyRepoListViewModel

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

    private val homeVm: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentView<ActivityHomeBinding>(
            this, R.layout.activity_home
        )
        binding.lifecycleOwner = this
        binding.vm = homeVm
        initViews()
        initData()
    }

    private fun initData() {
        homeVm.initData(this)
    }

    private fun initViews() {
        setupRepoListFragment()
    }

    private fun setupRepoListFragment() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fl_container,
                RepoListFragment.newInstance(RepoListFragment.REPO_TYPE_MY)
            )
            .commit()
    }
}