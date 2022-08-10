package com.hua.github_app.ui.adapter

import android.view.View
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hua.github_app.R
import com.hua.github_app.databinding.LayoutItemRepositoryBinding
import com.hua.github_app.http.entity.Repository

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class RepositoryAdapter constructor(
    data: MutableList<Repository>? = null
) : BaseQuickAdapter<Repository, BaseDataBindingHolder<LayoutItemRepositoryBinding>>(
    R.layout.layout_item_repository,
    data
) {
    override fun convert(
        holder: BaseDataBindingHolder<LayoutItemRepositoryBinding>,
        item: Repository
    ) {
        holder.dataBinding?.item = item
        holder.dataBinding?.ivUserAvatar?.run {
            Glide.with(holder.itemView).load(item.owner?.avatarUrl)
        }
    }
}

