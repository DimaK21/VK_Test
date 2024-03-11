package ru.kryu.vktest.domain

import kotlinx.coroutines.flow.Flow
import ru.kryu.vktest.domain.model.PageGoods
import ru.kryu.vktest.util.Resource

interface GoodsRepository {
    fun getGoods(skip: Int, limit: Int): Flow<Resource<PageGoods>>
}