package com.example.avitotechweather.data.api

import com.example.avitotechweather.domain.models.geocoder.YandexGeocoder
import com.example.avitotechweather.util.Constants.GEOCODER_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocoderApi {
    @GET("?apikey=$GEOCODER_API_KEY&format=json")
    suspend fun getCityPosition(@Query(value = "geocode", encoded = true) geocode: String): Response<YandexGeocoder>
}