package ru.kryu.vktest.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.kryu.vktest.data.dto.GoodsResponse

interface ApiService {

    @GET("products")
    suspend fun getGoods(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): GoodsResponse
}