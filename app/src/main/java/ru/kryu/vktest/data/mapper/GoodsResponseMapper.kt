package ru.kryu.vktest.data.mapper

import ru.kryu.vktest.data.dto.GoodsResponse
import ru.kryu.vktest.domain.model.PageGoods

object GoodsResponseMapper {

    fun map(goodsResponse: GoodsResponse): PageGoods = PageGoods(
        limit = goodsResponse.limit,
        skip = goodsResponse.skip,
        total = goodsResponse.total,
        listGoods = goodsResponse.products.map { ProductMapper.map(it) }
    )
}