package com.example.avitotechweather.data.repository

import com.example.avitotechweather.domain.models.Weather
import com.example.avitotechweather.domain.repository.WeatherRepository

class WeatherRepositoryImpl: WeatherRepository {

    private var weather: Weather? = null

    override fun getWeather(): Weather {
        return this.weather!!
    }

    override fun setWeather(weather: Weather) {
        this.weather = weather
    }

}