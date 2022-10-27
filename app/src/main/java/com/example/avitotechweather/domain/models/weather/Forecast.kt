package com.example.avitotechweather.domain.models.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val date: String,
    val parts: Parts
): Parcelable