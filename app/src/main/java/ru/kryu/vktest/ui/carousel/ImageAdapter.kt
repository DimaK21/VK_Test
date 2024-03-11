package ru.kryu.vktest.ui.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.kryu.vktest.R

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    val imageList: MutableList<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(parent)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    class ImageViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.carousel, parent, false)
        ) {

        private var carouselImageView: ImageView = itemView.findViewById(R.id.carouselImageView)

        fun bind(image: String) {
            Glide.with(itemView)
                .load(image)
                .placeholder(R.drawable.placeholder)
                .transform(
                    CenterCrop(),
                    RoundedCorners(itemView.resources.getDimensionPixelSize(R.dimen.card_corner))
                )
                .into(carouselImageView)
        }
    }
}