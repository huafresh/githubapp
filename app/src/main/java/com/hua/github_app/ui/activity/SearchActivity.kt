package com.hua.github_app.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import com.hua.github_app.R
import com.hua.github_app.base.BaseActivity
import com.hua.github_app.databinding.ActivitySearchBinding
import com.hua.github_app.ui.fragment.RepositoryListFragment
import com.hua.github_app.ui.viewmodel.SearchViewModel

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
class SearchActivity : BaseActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar(binding.toolbar)
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, RepositoryListFragment())
            .commit()
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
                searchVm.onQueryTextSubmit(this@SearchActivity, query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchVm.onQueryTextChange(this@SearchActivity, newText)
                return true
            }
        })
    }
}