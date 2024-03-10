package ru.kryu.vktest.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.kryu.vktest.domain.model.Goods

object GoodsDiffCallback: DiffUtil.ItemCallback<Goods>() {
    override fun areItemsTheSame(oldItem: Goods, newItem: Goods) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Goods, newItem: Goods) =
        oldItem == newItem
}