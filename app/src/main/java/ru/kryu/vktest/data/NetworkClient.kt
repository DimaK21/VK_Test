package ru.kryu.vktest.data

import ru.kryu.vktest.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(request: Any): Response
}