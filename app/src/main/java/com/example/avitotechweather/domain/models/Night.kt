package com.example.avitotechweather.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Night(
    val condition: String,
    val temp_max: Int,
    val temp_min: Int
): Parcelable