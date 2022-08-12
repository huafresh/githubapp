package com.hua.github_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hua.github_app.R
import com.hua.github_app.base.BaseFragment
import com.hua.github_app.databinding.FragmentListBinding
import com.hua.github_app.loadview.LoadViewHelper
import com.hua.github_app.ui.adapter.RepoListAdapter
import com.hua.github_app.ui.viewmodel.BaseRepoListViewModel

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class RepoListFragment : BaseFragment() {
    companion object {
        private const val TAG = "RepoListFragment"

        fun newInstance(vm: BaseRepoListViewModel): RepoListFragment {
            return RepoListFragment().apply { this.vm = vm }
        }
    }

    private lateinit var vm: BaseRepoListViewModel

    override fun layoutId(): Int {
        return R.layout.fragment_list
    }

    private var loadViewHelper: LoadViewHelper? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val binding = view?.let { FragmentListBinding.bind(it) }
        if (binding != null) {
            initViews(binding)
            initData()
            observes(binding)
        }
    }

    private fun initData() {
        vm.initData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observes(binding: FragmentListBinding) {
        vm.observeShowingDialog()
        vm.isRefreshing.observe(viewLifecycleOwner) { isRefreshing ->
            binding.swipeRefreshLayout.isRefreshing = isRefreshing
        }
        val adapter = binding.recyclerView.adapter as RepoListAdapter
        vm.repositoryList.observe(viewLifecycleOwner) { dataList ->
            adapter.data = dataList.toMutableList()
            adapter.notifyDataSetChanged()
        }
        vm.loadMoreDataList.observe(viewLifecycleOwner) { appendList ->
            adapter.addData(appendList)
        }
        vm.loadHelpViewModel.run {
            viewType.observe(viewLifecycleOwner) { viewType ->
                loadViewHelper?.showWithType(viewType)
            }
        }
    }

    private fun initViews(binding: FragmentListBinding) {
        setupRecyclerView(binding.recyclerView)
        setupSwipeRefreshLayout(binding.swipeRefreshLayout)
    }

    private fun setupRecyclerView(rv: RecyclerView) {
        rv.layoutManager = LinearLayoutManager(rv.context)
        val adapter = RepoListAdapter()
        rv.adapter = adapter
        loadViewHelper = LoadViewHelper.wrap(rv)
    }

    private fun setupSwipeRefreshLayout(refreshLayout: SwipeRefreshLayout) {
        refreshLayout.setOnRefreshListener {
            vm.pullToRefresh()
        }
    }
}