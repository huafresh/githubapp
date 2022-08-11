package com.hua.github_app.ui.activity

import com.hua.github_app.ui.viewmodel.BaseRepoListViewModel

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
interface IRepoListHost {
    fun getRepoListViewModel(): BaseRepoListViewModel
}