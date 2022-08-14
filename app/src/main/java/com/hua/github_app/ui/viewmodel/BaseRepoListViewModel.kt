package com.hua.github_app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.material.snackbar.Snackbar
import com.hjq.toast.ToastUtils
import com.hua.github_app.entity.Repository
import com.hua.github_app.ui.adapter.RepoListAdapter
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
    private var loadMoreRunning = false

    private val _repositoryList = MutableLiveData<List<Repository>>()
    val repositoryList: LiveData<List<Repository>> = _repositoryList

    private val _loadMoreDataList = MutableLiveData<List<Repository>>()
    val loadMoreDataList: LiveData<List<Repository>> = _loadMoreDataList

    val showSnackBar: EventLiveData<String> = EventLiveData()

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
            if (loadMoreRunning) {
                return@launchMain
            }
            loadMoreRunning = true
            val appendList = withContext(Dispatchers.IO) {
                fetchRepoListWithPage(page + 1)
            }
            val newList = mutableListOf<Repository>().apply {
                addAll(_repositoryList.value ?: emptyList())
                addAll(appendList)
            }
            _loadMoreDataList.value = newList
            loadMoreRunning = false
            LogUtil.i(TAG, "loadMoreData, page=${page + 1}")
            page++
        }, {
            LogUtil.e(TAG, "loadMoreData", it)
            loadMoreRunning = false
        })
    }

    fun onClickRvItem(adapter: RepoListAdapter, position: Int) {
        launchMain({
            val itemData = adapter.getItem(position)
            showSnackBar.value = itemData.name
        }, {
            LogUtil.e(TAG, "onClickRvItem", it)
        })
    }

    /**
     * notice: this method will be called on IO thread.
     */
    protected abstract suspend fun fetchRepoListWithPage(page: Int): List<Repository>
}