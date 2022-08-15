package com.hua.github_app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.hua.github_app.R
import com.hua.github_app.databinding.FragmentListBinding
import com.hua.github_app.ext.isScrollToEnd
import com.hua.github_app.lib.loadview.LoadViewHelper
import com.hua.github_app.ui.activity.SearchActivity
import com.hua.github_app.ui.adapter.RepoListAdapter
import com.hua.github_app.ui.viewmodel.BaseRepoListViewModel
import com.hua.github_app.ui.viewmodel.MyRepoListViewModel
import com.hua.github_app.ui.viewmodel.SearchRepoListViewModel

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class RepoListFragment : BaseFragment<FragmentListBinding>() {
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

    private lateinit var vm: BaseRepoListViewModel
    private var loadViewHelper: LoadViewHelper? = null

    /**
     * Invoked when recyclerView scroll state changed
     */
    var rvScrollStateChangedListener: ((Int) -> Unit)? = null

    override fun createBinding(container: ViewGroup?): FragmentListBinding {
        return FragmentListBinding.inflate(layoutInflater, container, false)
    }

    override fun initData() {
        vm.initData()
    }

    override fun initViews(binding: FragmentListBinding) {
        setupRecyclerView(binding.recyclerView)
        setupSwipeRefreshLayout(binding.swipeRefreshLayout)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun addObserves(binding: FragmentListBinding) {
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
        vm.showSnackBar.observe(viewLifecycleOwner) { message ->
            Snackbar.make(binding.recyclerView, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView(rv: RecyclerView) {
        rv.layoutManager = LinearLayoutManager(rv.context)
        val adapter = RepoListAdapter()
        adapter.setOnItemClickListener { _, view, position ->
            vm.onClickRvItem(adapter, position)
        }
        rv.adapter = adapter
        loadViewHelper = LoadViewHelper.wrap(rv)
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                    rv.isScrollToEnd()
                ) {
                    vm.loadMoreData()
                }
                rvScrollStateChangedListener?.invoke(newState)
            }
        })
    }

    private fun setupSwipeRefreshLayout(refreshLayout: SwipeRefreshLayout) {
        refreshLayout.setOnRefreshListener {
            vm.pullToRefresh()
        }
        refreshLayout.setColorSchemeColors(refreshLayout.context.getColor(R.color.primary_color))
    }

    fun onQueryTextSubmit(activity: SearchActivity, query: String?) {
        (vm as? SearchRepoListViewModel)?.onQueryTextSubmit(activity, query)
    }

    fun setViewModel(viewModel: BaseRepoListViewModel) {
        this.vm = viewModel
    }
}