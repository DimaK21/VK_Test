package ru.kryu.vktest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kryu.vktest.domain.GoodsUseCase

class GoodsViewModel(private val goodsUseCase: GoodsUseCase) : ViewModel() {

    private val _screenState: MutableLiveData<GoodsScreenState> = MutableLiveData()
    val screenState: LiveData<GoodsScreenState>
        get() = _screenState
}