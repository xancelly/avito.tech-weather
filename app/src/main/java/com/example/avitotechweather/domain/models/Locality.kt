package com.example.avitotechweather.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Locality(
    val id: Int,
    val name: String
): Parcelable