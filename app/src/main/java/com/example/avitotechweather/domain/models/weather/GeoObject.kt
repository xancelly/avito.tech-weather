package com.example.avitotechweather.domain.models.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeoObject(
    val district: Disctric?,
    val locality: Locality,
    val province: Province
): Parcelable {
}
