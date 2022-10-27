package com.example.avitotechweather.data.api

import com.example.avitotechweather.domain.models.kladr.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KladrApi {
    @GET("/api.php&limit=10&contentType=city&withParent=1")
    suspend fun getAddress(@Query("query") query: String): Response<SearchResult>
}