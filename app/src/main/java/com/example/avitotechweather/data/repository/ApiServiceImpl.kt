package com.example.avitotechweather.data.repository

import com.example.avitotechweather.data.api.ApiService
import javax.inject.Inject

class ApiServiceImpl
@Inject
constructor(private val apiService: ApiService) {
    suspend fun getWeather(lat: Double, lon: Double) = apiService.getWeather(latitude = lat, longitude = lon)
}