package com.example.avitotechweather.data.repository

import com.example.avitotechweather.data.api.WeatherApiService
import javax.inject.Inject

class WeatherApiServiceImpl
@Inject
constructor(private val weatherApiService: WeatherApiService) {
    suspend fun getWeather(lat: Double, lon: Double) = weatherApiService.getWeather(lat = lat, lon = lon)
}