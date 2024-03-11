package ru.kryu.vktest.presentation

import ru.kryu.vktest.domain.model.Goods
import ru.kryu.vktest.util.ErrorType

sealed class GoodsScreenState {
    data object Loading : GoodsScreenState()
    data object Empty : GoodsScreenState()
    data class Content(val content: List<Goods>) : GoodsScreenState()
}