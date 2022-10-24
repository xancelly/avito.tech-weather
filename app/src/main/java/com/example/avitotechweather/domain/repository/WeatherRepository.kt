package com.example.avitotechweather.domain.repository

import com.example.avitotechweather.domain.models.Weather

interface WeatherRepository {
    fun getWeather(): Weather

    fun setWeather(weather: Weather)
}