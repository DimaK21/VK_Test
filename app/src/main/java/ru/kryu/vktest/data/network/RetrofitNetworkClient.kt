package ru.kryu.vktest.data.network

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kryu.vktest.data.NetworkClient
import ru.kryu.vktest.data.dto.GoodsRequest
import ru.kryu.vktest.data.dto.Response
import ru.kryu.vktest.util.ConnectionChecker

class RetrofitNetworkClient(
    private val apiService: ApiService,
    private val context: Context,
) : NetworkClient {

    override suspend fun doRequest(request: Any): Response {
        if (!ConnectionChecker.isConnected(context)) {
            return Response().apply { resultCode = CODE_NO_INTERNET }
        }
        return withContext(Dispatchers.IO) {
            when (request) {
                is GoodsRequest -> getGoods(request.skip, request.limit)
                else -> Response().apply { resultCode = CODE_WRONG_REQUEST }
            }
        }
    }

    private suspend fun getGoods(skip: Int, limit: Int): Response {
        return try {
            apiService.getGoods(skip = skip, limit = limit).apply { resultCode = CODE_SUCCESS }
        } catch (e: Throwable) {
            Response().apply { resultCode = CODE_SERVER_ERROR }
        }
    }

    companion object {
        const val CODE_NO_INTERNET = -1
        const val CODE_SUCCESS = 200
        const val CODE_WRONG_REQUEST = 400
        const val CODE_SERVER_ERROR = 500
    }
}