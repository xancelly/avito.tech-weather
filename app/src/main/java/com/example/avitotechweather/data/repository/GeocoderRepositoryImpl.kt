package com.example.avitotechweather.data.repository

import com.example.avitotechweather.data.api.GeocoderApi
import com.example.avitotechweather.domain.models.geocoder.YandexGeocoder
import com.example.avitotechweather.domain.repository.GeocoderRepository
import retrofit2.Response
import javax.inject.Inject

class GeocoderRepositoryImpl
@Inject
constructor(private val geocoderApi: GeocoderApi): GeocoderRepository {
    override suspend fun getCityPosition(geocode: String): Response<YandexGeocoder> = geocoderApi.getCityPosition(geocode)

}