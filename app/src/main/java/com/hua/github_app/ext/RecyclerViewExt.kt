package com.hua.github_app.ext

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 2022/8/12.
 *
 * @author hua
 */
fun RecyclerView.isScrollToEnd(): Boolean {
    val recyclerView = this
    val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return false
    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
    val visibleItemCount = layoutManager.childCount
    val totalItemCount = layoutManager.itemCount
    val state = recyclerView.scrollState
    return visibleItemCount > 0 &&
            lastVisibleItemPosition == totalItemCount - 1 &&
            state == RecyclerView.SCROLL_STATE_IDLE
}