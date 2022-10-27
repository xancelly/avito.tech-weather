package com.example.avitotechweather.domain.models.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tzinfo(
    val abbr: String,
    val dst: Boolean,
    val name: String,
    val offset: Int
): Parcelable