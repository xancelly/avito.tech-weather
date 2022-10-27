package com.example.avitotechweather.domain.repository

import com.example.avitotechweather.domain.models.weather.Weather
import retrofit2.Response


interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): Response<Weather>
}