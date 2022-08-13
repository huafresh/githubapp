package com.hua.github_app.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil.setContentView
import com.hua.github_app.R
import com.hua.github_app.base.BaseActivity
import com.hua.github_app.databinding.ActivityHomeBinding
import com.hua.github_app.ui.fragment.RepoListFragment
import com.hua.github_app.ui.viewmodel.BaseRepoListViewModel
import com.hua.github_app.ui.viewmodel.HomeViewModel
import com.hua.github_app.ui.viewmodel.MyRepoListViewModel
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class HomeActivity : BaseActivity() {

    companion object {
        private const val TAG = "MainActivity"

        fun show(context: Context) {
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
        initViews(binding)
        observers(binding)
        homeVm.initData(this)
    }

    private fun observers(binding: ActivityHomeBinding) {
        homeVm.drawerOpen.observe(this) { open ->
            val slider = binding.slider
            if (open) {
                slider.drawerLayout?.openDrawer(slider)
            } else {
                slider.drawerLayout?.closeDrawer(slider)
            }
        }
    }

    private fun initViews(binding: ActivityHomeBinding) {
        setupToolbar(binding.toolbar)
        setupRepoListFragment()
        setupSlider(binding.slider)
    }

    private fun setupToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        toolbar.navigationIcon = DrawerArrowDrawable(this).apply {
            this.color = getColor(R.color.icon_color)
        }
        toolbar.setNavigationOnClickListener {
            homeVm.onClickDrawerEntrance(it)
        }
    }

    private fun setupSlider(slider: MaterialDrawerSliderView) {
        val item1 = PrimaryDrawerItem().apply {
            nameRes = R.string.login_failed
            identifier = 1
        }
        val item2 = SecondaryDrawerItem().apply {
            nameRes = R.string.load_error_tips
            identifier = 2
        }
        slider.itemAdapter.add(
            item1,
            DividerDrawerItem(),
            item2,
            SecondaryDrawerItem().apply {
                nameRes = R.string.text_is_empty
            }
        )

        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            Toast.makeText(this, "pos=$position", Toast.LENGTH_SHORT).show()
            false
        }

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