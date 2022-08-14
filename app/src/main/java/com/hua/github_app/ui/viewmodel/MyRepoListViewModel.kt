package com.hua.github_app.ui.viewmodel

import com.hua.github_app.http.AppRetrofit
import com.hua.github_app.entity.Repository
import com.hua.github_app.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
class MyRepoListViewModel : BaseRepoListViewModel() {
    companion object {
        private const val TAG = "MyRepoListViewModel"
    }

    override suspend fun fetchRepoListWithPage(page: Int): List<Repository> {
        LogUtil.i(TAG, "fetchRepositoryWithPage: page=$page")
        val list = AppRetrofit.getRepoService()
            .getUserRepos(page, "all", "updated", "desc")
        LogUtil.i(TAG, "fetchRepositoryWithPage: result size=${list.size}")
        return list
    }

}