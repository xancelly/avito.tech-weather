package com.example.avitotechweather.domain.models.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Morning(
    val condition: String,
    val temp_max: Int,
    val temp_min: Int
): Parcelable