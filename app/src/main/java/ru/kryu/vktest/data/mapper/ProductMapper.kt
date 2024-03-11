package ru.kryu.vktest.data.mapper

import ru.kryu.vktest.data.dto.Product
import ru.kryu.vktest.domain.model.Goods

object ProductMapper {
    fun map(product: Product): Goods = Goods(
        id = product.id,
        title = product.title,
        description = product.description,
        thumbnail = product.thumbnail,
        price = product.price,
        discountPercentage = product.discountPercentage,
        images = product.images,
    )
}