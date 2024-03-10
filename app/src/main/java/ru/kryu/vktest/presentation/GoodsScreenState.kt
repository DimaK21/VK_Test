package ru.kryu.vktest.presentation

import ru.kryu.vktest.domain.model.Goods
import ru.kryu.vktest.util.ErrorType

sealed class GoodsScreenState {
    data object Loading : GoodsScreenState()
    data object Empty : GoodsScreenState()
    data class Error(val error: ErrorType) : GoodsScreenState()
    data class Content(val content: List<Goods>) : GoodsScreenState()
    data object LoadingNextPage : GoodsScreenState()
    data class LoadingNextPageError(val error: ErrorType) : GoodsScreenState()
}