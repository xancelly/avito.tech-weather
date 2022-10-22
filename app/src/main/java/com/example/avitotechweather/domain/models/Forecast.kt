package com.example.avitotechweather.domain.models

data class Forecast(
    val date: String,
    val condition: String,
    val day_temp: Int,
    val night_temp: Int
)