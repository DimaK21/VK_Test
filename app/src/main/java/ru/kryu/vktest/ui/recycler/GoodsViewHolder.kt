package ru.kryu.vktest.ui.recycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.kryu.vktest.R
import ru.kryu.vktest.databinding.GoodsItemBinding
import ru.kryu.vktest.domain.model.Goods

class GoodsViewHolder(private val binding: GoodsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(goods: Goods) {
        binding.tvTitle.text = goods.title
        binding.tvDescription.text = goods.description
        Glide.with(itemView)
            .load(goods.thumbnail)
            .placeholder(R.drawable.placeholder)
            .transform(
                CenterCrop(),
                RoundedCorners(itemView.resources.getDimensionPixelSize(R.dimen.card_corner))
            )
            .into(binding.ivThumbnail)
    }
}