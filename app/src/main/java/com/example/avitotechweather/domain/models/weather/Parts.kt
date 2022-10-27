package com.example.avitotechweather.domain.models.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Parts(
    val day: Day,
    val evening: Evening,
    val morning: Morning,
    val night: Night
): Parcelable