package com.hua.github_app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hua.github_app.base.BaseViewModel
import com.hua.github_app.http.AppRetrofit
import com.hua.github_app.http.api.RepoService
import com.hua.github_app.http.entity.Repository
import com.hua.github_app.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class RepositoryListViewModel : BaseViewModel() {

    companion object {
        private const val TAG = "RepositoryListViewModel"
    }

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private var page = 0

    private val _repositoryList = MutableLiveData<List<Repository>>()
    val repositoryList: LiveData<List<Repository>> = _repositoryList

    private val _loadMoreDataList = MutableLiveData<List<Repository>>()
    val loadMoreDataList: LiveData<List<Repository>> = _loadMoreDataList

    fun initData() {
        launchMain({
            page = 0
            _isRefreshing.value = true
            val list = fetchRepositoryWithPage(page)
            _repositoryList.value = list
            _isRefreshing.value = false
        }, {
            LogUtil.e(TAG, "fetchRepositoryList", it)
        })
    }

    fun pullToRefresh() {
        launchMain({
            page = 0
            val list = fetchRepositoryWithPage(page)
            _repositoryList.value = list
            _isRefreshing.value = false
        }, {
            LogUtil.e(TAG, "pullToRefresh", it)
            _isRefreshing.value = false
        })
    }

    fun loadMoreData() {
        launchMain({
            val appendList = fetchRepositoryWithPage(++page)
            val newList = mutableListOf<Repository>().apply {
                addAll(_repositoryList.value ?: emptyList())
                addAll(appendList)
            }
            _loadMoreDataList.value = newList
        }, {
            LogUtil.e(TAG, "loadMoreData", it)
        })
    }

    private suspend fun fetchRepositoryWithPage(page: Int): List<Repository> {
        LogUtil.i(TAG, "fetchRepositoryWithPage: page=$page")
        val list = withContext(Dispatchers.IO) {
            AppRetrofit.getRepoService()
                .getUserRepos(page, "all", "updated", "desc")
        }
        LogUtil.i(TAG, "fetchRepositoryWithPage: result size=${list.size}")
        return list
    }
}