package ru.kryu.vktest.data.dto

data class GoodsRequest(
    val skip: Int = 0,
    val limit: Int = 20,
)