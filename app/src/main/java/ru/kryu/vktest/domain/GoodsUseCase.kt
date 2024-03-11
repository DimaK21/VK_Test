package ru.kryu.vktest.domain

import kotlinx.coroutines.flow.Flow
import ru.kryu.vktest.domain.model.PageGoods
import ru.kryu.vktest.util.Resource

class GoodsUseCase(private val goodsRepository: GoodsRepository) {

    fun execute(skip: Int, limit: Int): Flow<Resource<PageGoods>> {
        return goodsRepository.getGoods(skip = skip, limit = limit)
    }
}