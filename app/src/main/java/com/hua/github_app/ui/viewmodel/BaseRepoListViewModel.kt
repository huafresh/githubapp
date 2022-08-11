package com.hua.github_app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hua.github_app.base.BaseViewModel
import com.hua.github_app.http.entity.Repository
import com.hua.github_app.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
abstract class BaseRepoListViewModel : BaseViewModel() {

    companion object {
        private const val TAG = "BaseRepoListViewModel"
    }

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private var page = 0

    private val _repositoryList = MutableLiveData<List<Repository>>()
    val repositoryList: LiveData<List<Repository>> = _repositoryList

    private val _loadMoreDataList = MutableLiveData<List<Repository>>()
    val loadMoreDataList: LiveData<List<Repository>> = _loadMoreDataList

    val loadHelpViewModel = LoadHelpViewModel()

    fun initData() {
        launchMain({
            page = 0
            _isRefreshing.value = true
            val list = withContext(Dispatchers.IO) {
                fetchRepoListWithPage(page)
            }
            _repositoryList.value = list
            if (list.isEmpty()) {
                loadHelpViewModel.showEmpty()
            } else {
                loadHelpViewModel.showContent()
            }
            _isRefreshing.value = false
        }, {
            LogUtil.e(TAG, "initData", it)
            _isRefreshing.value = false
            loadHelpViewModel.showError()
        })
    }

    fun pullToRefresh() {
        initData()
    }

    fun loadMoreData() {
        launchMain({
            val appendList = withContext(Dispatchers.IO) {
                fetchRepoListWithPage(++page)
            }
            val newList = mutableListOf<Repository>().apply {
                addAll(_repositoryList.value ?: emptyList())
                addAll(appendList)
            }
            _loadMoreDataList.value = newList
        }, {
            LogUtil.e(TAG, "loadMoreData", it)
        })
    }

    /**
     * notice: this method will be called on IO thread.
     */
    protected abstract suspend fun fetchRepoListWithPage(page: Int): List<Repository>
}