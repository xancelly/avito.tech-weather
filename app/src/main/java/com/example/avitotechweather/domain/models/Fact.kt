package com.example.avitotechweather.domain.models

data class Fact(
    val condition: String,
    val daytime: String,
    val humidity: Int,
    val obs_time: Int,
    val pressure_mm: Int,
    val temp: Int,
    val uptime: Int,
    val wind_speed: Double
)