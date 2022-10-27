package com.example.avitotechweather.domain.repository

import com.example.avitotechweather.domain.models.kladr.SearchResult
import retrofit2.Response

interface KladrRepository {
    suspend fun getAddress(query: String): Response<SearchResult>
}