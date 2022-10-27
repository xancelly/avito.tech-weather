package com.example.avitotechweather.data.repository

import com.example.avitotechweather.data.api.KladrApi
import com.example.avitotechweather.domain.models.kladr.SearchResult
import com.example.avitotechweather.domain.repository.KladrRepository
import retrofit2.Response
import javax.inject.Inject

class KladrRepositoryImpl
@Inject
constructor(private val kladrApi: KladrApi): KladrRepository {
    override suspend fun getAddress(query: String): Response<SearchResult> = kladrApi.getAddress(query = query)
}