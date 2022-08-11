package com.hua.github_app.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.hua.github_app.R
import com.hua.github_app.base.BaseActivity
import com.hua.github_app.databinding.ActivitySearchBinding
import com.hua.github_app.ui.fragment.RepoListFragment
import com.hua.github_app.ui.viewmodel.BaseRepoListViewModel
import com.hua.github_app.ui.viewmodel.SearchRepoListViewModel
import com.hua.github_app.ui.viewmodel.SearchViewModel
import com.hua.github_app.utils.LogUtil

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
class SearchActivity : BaseActivity(), IRepoListHost {

    companion object {
        private const val TAG = "SearchActivity"

        fun jumpSearch(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

    private val searchVm = SearchViewModel()
    private val searchRepoVm = SearchRepoListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews(binding)
        initData()
        observes(binding)
    }

    private fun initData() {
        searchVm.initData(this)
    }

    private fun setupRepoListFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, RepoListFragment())
            .commit()
    }

    private fun observes(binding: ActivitySearchBinding) {
        searchVm.title.observe(this) { title ->
            binding.toolbar.title = title
        }
    }

    private fun initViews(binding: ActivitySearchBinding) {
        setupRepoListFragment()
        setupToolbar(binding.toolbar)
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
                searchRepoVm.onQueryTextSubmit(this@SearchActivity, query)
                searchVm.onQueryTextSubmit(this@SearchActivity, query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchRepoVm.onQueryTextChange(newText)
                return true
            }
        })
        searchVm.inputMode.observe(this) { inputMode ->
            LogUtil.i(TAG, "setupSearchView: inpuMode=$inputMode")
            if (!inputMode) {
                searchView.setQuery("", false)
                searchView.isIconified = true
            } else {
                searchView.isIconified = false
            }
        }
    }

    override fun getRepoListViewModel(): BaseRepoListViewModel {
        return searchRepoVm
    }
}