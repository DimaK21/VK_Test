package ru.kryu.vktest.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.kryu.vktest.domain.model.Goods

class GoodsDiffCallback(
    private val oldList: List<Goods>,
    private val newList: List<Goods>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}