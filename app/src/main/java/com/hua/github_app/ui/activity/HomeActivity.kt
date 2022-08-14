package com.hua.github_app.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil.setContentView
import com.hua.github_app.R
import com.hua.github_app.databinding.ActivityHomeBinding
import com.hua.github_app.ui.fragment.RepoListFragment
import com.hua.github_app.ui.viewmodel.HomeViewModel
import com.mikepenz.materialdrawer.holder.ImageHolder
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.iconRes
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.widget.AccountHeaderView
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    companion object {
        private const val TAG = "MainActivity"
        private const val LOGOUT_DRAWER_ITEM_ID = 1

        fun show(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    private val homeVm: HomeViewModel by viewModels()

    override fun initBinding(): ActivityHomeBinding {
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vm = homeVm
        return binding
    }

    override fun initData() {
        homeVm.initData(this)
    }

    override fun initViews(binding: ActivityHomeBinding) {
        setupToolbar(binding.toolbar)
        setupRepoListFragment()
        setupDrawerSlider(binding.slider)
    }

    override fun addObserves(binding: ActivityHomeBinding) {
        homeVm.drawerOpen.observe(this) { open ->
            val slider = binding.slider
            if (open) {
                slider.drawerLayout?.openDrawer(slider)
            } else {
                slider.drawerLayout?.closeDrawer(slider)
            }
        }
        homeVm.user.observe(this) { user ->
            val headerView = binding.slider.headerView as? AccountHeaderView
            val profileItem = headerView?.profiles?.firstOrNull()
            if (profileItem != null) {
                profileItem.name = StringHolder(user.login)
                profileItem.description = StringHolder(user.createdAt?.toString())
                profileItem.icon = ImageHolder(user.avatarUrl ?: "")
            }
            headerView?.updateHeaderAndList()
        }
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

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    private fun setupDrawerSlider(slider: MaterialDrawerSliderView) {
        val headerView = AccountHeaderView(this)
        headerView.selectionListEnabled = false
        headerView.background = AppCompatResources.getDrawable(
            this@HomeActivity,
            R.color.primary_color
        )
        val profileItem = object : ProfileDrawerItem() {}
        profileItem.textColor = ColorStateList.valueOf(getColor(R.color.title_color))
        profileItem.iconRes = R.drawable.logo_big
        profileItem.name = StringHolder("nickName")
        profileItem.description = StringHolder("create time")
        headerView.addProfiles(profileItem)
        slider.headerView = headerView
        val logoutItem = PrimaryDrawerItem().apply {
            icon = ImageHolder(R.drawable.ic_logout)
            nameRes = R.string.login_out
            identifier = LOGOUT_DRAWER_ITEM_ID.toLong()
            isSelectable = false
        }
        slider.itemAdapter.add(logoutItem, DividerDrawerItem())

        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            if (position == LOGOUT_DRAWER_ITEM_ID) {
                homeVm.onclickLogout()
                true
            } else false
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