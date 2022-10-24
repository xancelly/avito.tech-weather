package com.example.avitotechweather.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val fact: Fact,
    val forecasts: List<Forecast>,
    val geo_object: GeoObject,
    val info: Info,
    val now: Int,
    val now_dt: String
): Parcelable