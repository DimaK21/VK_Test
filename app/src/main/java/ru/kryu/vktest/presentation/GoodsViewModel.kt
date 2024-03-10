package ru.kryu.vktest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kryu.vktest.domain.GoodsUseCase
import ru.kryu.vktest.domain.model.PageGoods
import ru.kryu.vktest.util.ErrorType
import ru.kryu.vktest.util.Resource
import ru.kryu.vktest.util.SingleLiveEvent

class GoodsViewModel(private val goodsUseCase: GoodsUseCase) : ViewModel() {

    private val _stateLiveData: MutableLiveData<GoodsScreenState> = MutableLiveData()
    val stateLiveData: LiveData<GoodsScreenState>
        get() = _stateLiveData
    private val _toastLiveData = SingleLiveEvent<ErrorType>()
    val toastLiveData: LiveData<ErrorType>
        get() = _toastLiveData
    private lateinit var lastState: GoodsScreenState

    init {
        getGoods()
    }

    fun getGoods() {
        lastState = _stateLiveData.value ?: GoodsScreenState.Loading
        _stateLiveData.postValue(GoodsScreenState.Loading)
        viewModelScope.launch {
            when (lastState) {
                is GoodsScreenState.Loading, GoodsScreenState.Empty -> {
                    goodsUseCase.execute(skip = 0, limit = LIMIT)
                        .collect { processResult(it) }
                }

                is GoodsScreenState.Content -> {
                    goodsUseCase.execute(
                        skip = (lastState as GoodsScreenState.Content).content.size,
                        limit = LIMIT
                    )
                        .collect { processResult(it) }
                }
            }
        }
    }

    private fun processResult(resource: Resource<PageGoods>) {
        when (resource) {
            is Resource.Error -> {
                when (resource.errorType) {
                    ErrorType.NO_INTERNET, ErrorType.REMOTE_ERROR -> {
                        _toastLiveData.postValue(resource.errorType!!)
                        if (lastState is GoodsScreenState.Loading || lastState is GoodsScreenState.Empty) {
                            _stateLiveData.postValue(GoodsScreenState.Empty)
                        } else {
                            _stateLiveData.postValue(lastState)
                        }
                    }

                    else -> _stateLiveData.postValue(GoodsScreenState.Empty)
                }
            }

            is Resource.Success -> {
                if (lastState is GoodsScreenState.Empty || resource.data!!.listGoods.isEmpty()) {
                    _stateLiveData.postValue(GoodsScreenState.Empty)
                } else {
                    _stateLiveData.postValue(
                        GoodsScreenState.Content(
                            if (lastState is GoodsScreenState.Content) {
                                (lastState as GoodsScreenState.Content).content + resource.data!!.listGoods
                            } else {
                                resource.data!!.listGoods
                            }
                        )
                    )
                }
            }
        }
    }

    companion object {
        const val LIMIT = 20
    }
}