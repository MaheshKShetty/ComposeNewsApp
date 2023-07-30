package com.cap.samplecompose.network

import com.cap.samplecompose.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkRepo @Inject constructor(private val service:APIInterface) {

    suspend fun getNewsList(): Flow<NewsResponse> {
        return flow {
            val result = service.getNewsList()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}