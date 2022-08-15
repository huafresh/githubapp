package com.hua.github_app.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.hua.github_app.R
import com.hua.github_app.databinding.ActivitySearchBinding
import com.hua.github_app.ext.hideSoftKeyboard
import com.hua.github_app.ui.fragment.RepoListFragment
import com.hua.github_app.ui.viewmodel.SearchRepoListViewModel
import com.hua.github_app.ui.viewmodel.SearchViewModel

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    companion object {
        private const val TAG = "SearchActivity"

        fun show(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    private val searchVm: SearchViewModel by viewModels()
    private val repoListFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fl_container) as? RepoListFragment
    }
    private var searchView: SearchView? = null

    override fun createBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun initData() {
        searchVm.initData(this)
    }

    override fun initViews(binding: ActivitySearchBinding) {
        setupRepoListFragment()
        setupToolbar(binding.toolbar)
    }

    override fun addObserves(binding: ActivitySearchBinding) {
        searchVm.title.observe(this) { title ->
            binding.toolbar.title = title
        }
    }

    private fun setupRepoListFragment() {
        var repoFragment = supportFragmentManager.findFragmentById(R.id.fl_container)
        if (repoFragment == null) {
            repoFragment = RepoListFragment.newInstance(RepoListFragment.REPO_TYPE_SEARCH)
            supportFragmentManager.beginTransaction()
                .add(R.id.fl_container, repoFragment)
                .commit()
        }
        (repoFragment as? RepoListFragment)?.run {
            setViewModel(SearchRepoListViewModel())
            rvScrollStateChangedListener = { newState ->
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    searchView?.enterCollapseState()
                    hideSoftKeyboard()
                }
            }
        }
    }

    private fun setupToolbar(toolBar: Toolbar) {
        setSupportActionBar(toolBar)
        supportActionBar?.run {
            title = null
            setDisplayHomeAsUpEnabled(true)
        }
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolBar.setNavigationOnClickListener { finish() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        if (searchItem != null) {
            searchItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_search_title)
            val searchView = searchItem.actionView as SearchView
            setupSearchView(searchView)
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                repoListFragment?.onQueryTextSubmit(this@SearchActivity, query)
                searchVm.onQueryTextSubmit(this@SearchActivity, query)
                searchView.enterCollapseState()
                hideSoftKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        searchView.isIconified = false
        this.searchView = searchView
    }

    private fun SearchView.enterCollapseState() {
        this.clearFocus()
        this.isIconified = true
        this.isIconified = true
    }
}