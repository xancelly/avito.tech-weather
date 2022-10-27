package com.example.avitotechweather.data.repository

import com.example.avitotechweather.data.api.WeatherApi
import com.example.avitotechweather.domain.models.weather.Weather
import com.example.avitotechweather.domain.repository.WeatherRepository
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl
@Inject
constructor(private val weatherApi: WeatherApi): WeatherRepository {
    override suspend fun getWeather(lat: Double, lon: Double): Response<Weather> = weatherApi.getWeather(lat = lat, lon = lon)

}