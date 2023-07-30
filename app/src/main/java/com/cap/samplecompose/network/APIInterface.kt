package com.cap.samplecompose.network

import com.cap.samplecompose.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

// API End Points
interface APIInterface {

    companion object {
        const val NEWS_LIST = "v2/everything?q=general&apiKey=f6a01a45089f45b18f8e2080adfcceff"
    }

    @GET(NEWS_LIST)
    suspend fun getNewsList(): NewsResponse

}