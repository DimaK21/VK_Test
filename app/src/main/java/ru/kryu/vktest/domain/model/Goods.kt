package ru.kryu.vktest.domain.model

data class Goods(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val price: Int,
    val discountPercentage: Double,
    val images: List<String>,
)
