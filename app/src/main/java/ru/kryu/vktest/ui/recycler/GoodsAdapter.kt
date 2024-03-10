package ru.kryu.vktest.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.kryu.vktest.databinding.GoodsItemBinding
import ru.kryu.vktest.domain.model.Goods

class GoodsAdapter : ListAdapter<Goods, GoodsViewHolder>(GoodsDiffCallback) {

    val goodsList: MutableList<Goods> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GoodsViewHolder(GoodsItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        holder.bind(goodsList[position])
    }
}