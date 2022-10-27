package com.example.avitotechweather.domain.repository

import com.example.avitotechweather.domain.models.geocoder.YandexGeocoder
import retrofit2.Response

interface GeocoderRepository {
    suspend fun getCityPosition(geocode: String): Response<YandexGeocoder>
}