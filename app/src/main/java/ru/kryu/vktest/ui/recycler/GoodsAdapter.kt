package ru.kryu.vktest.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.kryu.vktest.databinding.GoodsItemBinding
import ru.kryu.vktest.domain.model.Goods

class GoodsAdapter : RecyclerView.Adapter<GoodsViewHolder>() {

    private val goodsList: MutableList<Goods> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GoodsViewHolder(GoodsItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = goodsList.size

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        holder.bind(goodsList[position])
    }

    fun addGoods(newGoods: List<Goods>) {
        val diffCallback = GoodsDiffCallback(goodsList, newGoods)
        val diffGoods = DiffUtil.calculateDiff(diffCallback)
        goodsList.clear()
        goodsList.addAll(newGoods)
        diffGoods.dispatchUpdatesTo(this)
    }
}