package com.example.avitotechweather.data.api

import com.example.avitotechweather.domain.models.Weather
import com.example.avitotechweather.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("X-Yandex-API-Key: $API_KEY")
    @GET("forecast?limit=7&extra=false&")
    suspend fun getWeather(@Query("latitude") latitude: Double, @Query("longitude") longitude: Double): Response<Weather>
}