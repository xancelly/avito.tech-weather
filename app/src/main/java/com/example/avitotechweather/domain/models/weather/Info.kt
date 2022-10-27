package com.example.avitotechweather.domain.models.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val lat: Double,
    val lon: Double,
    val tzinfo: Tzinfo
): Parcelable