package ru.kryu.vktest.data.dto

data class GoodsDto(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)
