package com.hua.github_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hua.github_app.R
import com.hua.github_app.base.BaseFragment
import com.hua.github_app.databinding.FragmentListBinding
import com.hua.github_app.ext.isScrollToEnd
import com.hua.github_app.loadview.LoadViewHelper
import com.hua.github_app.ui.adapter.RepoListAdapter
import com.hua.github_app.ui.viewmodel.BaseRepoListViewModel
import com.hua.github_app.ui.viewmodel.MyRepoListViewModel
import com.hua.github_app.ui.viewmodel.SearchRepoListViewModel
import com.hua.github_app.utils.LogUtil

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class RepoListFragment : BaseFragment() {
    companion object {
        private const val TAG = "RepoListFragment"

        private const val KEY_REPO_TYPE = "KEY_REPO_TYPE"
        const val REPO_TYPE_MY = 0
        const val REPO_TYPE_SEARCH = 1

        fun newInstance(repoType: Int): RepoListFragment {
            return RepoListFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_REPO_TYPE, repoType)
                }
            }
        }
    }

    private var vm: BaseRepoListViewModel? = null

    override fun layoutId(): Int {
        return R.layout.fragment_list
    }

    private var loadViewHelper: LoadViewHelper? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentListBinding.bind(view)
        initData()
        initViews(binding)
        observes(binding)
    }

    private fun initData() {
        val repoType = arguments?.getInt(KEY_REPO_TYPE) ?: REPO_TYPE_MY
        vm = when (repoType) {
            REPO_TYPE_SEARCH -> {
                SearchRepoListViewModel()
            }
            else -> {
                MyRepoListViewModel()
            }
        }
        vm?.initData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observes(binding: FragmentListBinding) {
        val vm = vm ?: return
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
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        rv.isScrollToEnd()) {
                    vm?.loadMoreData()
                }
            }
        })
    }

    private fun setupSwipeRefreshLayout(refreshLayout: SwipeRefreshLayout) {
        refreshLayout.setOnRefreshListener {
            vm?.pullToRefresh()
        }
    }

    fun getViewModel(): BaseRepoListViewModel? {
        return vm
    }
}