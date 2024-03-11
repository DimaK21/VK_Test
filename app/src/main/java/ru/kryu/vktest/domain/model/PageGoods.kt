package ru.kryu.vktest.domain.model

data class PageGoods(
    val limit: Int,
    val listGoods: List<Goods>,
    val skip: Int,
    val total: Int
)
