package com.example.avitotechweather.data.api

import com.example.avitotechweather.domain.models.Weather
import com.example.avitotechweather.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("X-Yandex-API-Key: $API_KEY")
    @GET("forecast?lat={latitude}&lon={longitude}&limit=7&extra=false")
    suspend fun getWeather(@Path("latitude") latitude: Double, @Path("longitude") longitude: Double): Response<Weather>
}