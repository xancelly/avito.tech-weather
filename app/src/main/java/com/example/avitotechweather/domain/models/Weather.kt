package com.example.avitotechweather.domain.models

data class Weather(
    val fact: Fact,
    val forecasts: List<Forecast>,
    val locality: String,
    val now: Int,
    val now_dt: String,
    val province: String
)