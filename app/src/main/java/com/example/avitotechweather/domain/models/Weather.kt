package com.example.avitotechweather.domain.models

data class Weather(
    val fact: Fact,
    val forecasts: List<Forecast>,
    val geo_object: GeoObject,
    val info: Info,
    val now: Int,
    val now_dt: String
)