package com.example.avitotechweather.domain.models.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fact(
    val condition: String,
    val daytime: String,
    val humidity: Int,
    val obs_time: Int,
    val pressure_mm: Int,
    val temp: Int,
    val uptime: Int,
    val wind_speed: Double
): Parcelable