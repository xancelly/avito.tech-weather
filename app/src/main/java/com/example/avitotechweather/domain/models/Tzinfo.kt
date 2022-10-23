package com.example.avitotechweather.domain.models

data class Tzinfo(
    val abbr: String,
    val dst: Boolean,
    val name: String,
    val offset: Int
)