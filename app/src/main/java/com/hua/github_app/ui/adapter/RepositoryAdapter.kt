package com.hua.github_app.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.hua.github_app.R
import com.hua.github_app.databinding.LayoutItemRepositoryBinding
import com.hua.github_app.http.entity.Repository
import com.hua.github_app.image.ImageLoader

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
        val binding = holder.dataBinding
        if (binding != null) {
            binding.item = item
            binding.ivUserAvatar.run {
                ImageLoader.with(holder.itemView)
                    .load(item.owner?.avatarUrl)
                    .into(binding.ivUserAvatar)
            }
        }
    }
}

