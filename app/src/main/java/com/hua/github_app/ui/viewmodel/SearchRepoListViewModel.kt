package com.hua.github_app.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hua.github_app.R
import com.hua.github_app.http.AppRetrofit
import com.hua.github_app.http.entity.Repository
import com.hua.github_app.utils.LogUtil

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
class SearchRepoListViewModel : BaseRepoListViewModel() {
    companion object {
        private const val TAG = "SearchRepoListViewModel"
    }

    private var query: String? = null

    @SuppressLint("CheckResult")
    fun onQueryTextSubmit(context: Context, query: String?) {
        launchMain({
            if (query.isNullOrBlank()) {
                //Toasty.warning(context, context.getString(R.string.text_is_empty))
                return@launchMain
            }
            super.pullToRefresh()
        }, {
            LogUtil.e(TAG, "onQueryTextSubmit", it)
        })
    }

    fun onQueryTextChange(newText: String?) {
        this.query = newText
    }

    override suspend fun fetchRepoListWithPage(page: Int): List<Repository> {
        LogUtil.i(TAG, "search repo list: page=$page")
        if(query.isNullOrBlank()) return emptyList()
        val searchResult = AppRetrofit.getSearchService()
            .searchRepos(query, "stars", "desc", page)
        val list = searchResult.items ?: emptyList()
        LogUtil.i(TAG, "search repo list: list size=${list.size}")
        return list
    }
}