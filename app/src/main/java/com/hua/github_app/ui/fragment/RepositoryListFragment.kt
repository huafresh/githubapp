package com.hua.github_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hua.github_app.R
import com.hua.github_app.base.BaseFragment
import com.hua.github_app.databinding.FragmentListBinding
import com.hua.github_app.ui.adapter.RepositoryAdapter
import com.hua.github_app.ui.viewmodel.RepositoryListViewModel

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class RepositoryListFragment : BaseFragment() {
    private val vm: RepositoryListViewModel = RepositoryListViewModel()

    override fun layoutId(): Int {
        return R.layout.fragment_list
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.let {
            val binding = FragmentListBinding.bind(it)
            setupRecyclerView(binding.recyclerView)
            setupSwipeRefreshLayout(binding.swipeRefreshLayout)
        }
        observeShowingDialog(vm)
        vm.initData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView(rv: RecyclerView) {
        rv.layoutManager = LinearLayoutManager(rv.context)
        val adapter = RepositoryAdapter()
        rv.adapter = adapter
        vm.repositoryList.observe(this) { dataList ->
            adapter.data = dataList.toMutableList()
            adapter.notifyDataSetChanged()
        }
        vm.loadMoreDataList.observe(this) { appendList ->
            adapter.addData(appendList)
        }
    }

    private fun setupSwipeRefreshLayout(refreshLayout: SwipeRefreshLayout) {
        vm.isRefreshing.observe(this) { isRefreshing ->
            refreshLayout.isRefreshing = isRefreshing
        }
        refreshLayout.setOnRefreshListener {
            vm.pullToRefresh()
        }
    }
}