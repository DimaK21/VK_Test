package ru.kryu.vktest.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kryu.vktest.data.dto.GoodsRequest
import ru.kryu.vktest.data.dto.GoodsResponse
import ru.kryu.vktest.data.mapper.GoodsResponseMapper
import ru.kryu.vktest.data.network.RetrofitNetworkClient
import ru.kryu.vktest.domain.GoodsRepository
import ru.kryu.vktest.domain.model.PageGoods
import ru.kryu.vktest.util.ErrorType
import ru.kryu.vktest.util.Resource

class GoodsRepositoryImpl(private val networkClient: NetworkClient) : GoodsRepository {
    override fun getGoods(skip: Int, limit: Int): Flow<Resource<PageGoods>> = flow {
        val response = networkClient.doRequest(GoodsRequest(skip = skip, limit = limit))
        emit(
            when (response.resultCode) {
                RetrofitNetworkClient.CODE_SUCCESS -> {
                    if ((response as GoodsResponse).products.isEmpty()) {
                        Resource.Error(ErrorType.NO_CONTENT)
                    } else {
                        Resource.Success(GoodsResponseMapper.map(response))
                    }
                }

                RetrofitNetworkClient.CODE_NO_INTERNET -> {
                    Resource.Error(ErrorType.NO_INTERNET)
                }

                else -> {
                    Resource.Error(ErrorType.REMOTE_ERROR)
                }
            }
        )
    }
}