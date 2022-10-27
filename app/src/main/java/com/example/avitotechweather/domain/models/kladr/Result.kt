package com.example.avitotechweather.domain.models.kladr

data class Result(
    val cadnum: String,
    val contentType: String,
    val guid: String,
    val id: String,
    val ifnsfl: String,
    val ifnsul: String,
    val name: String,
    val okato: String,
    val oktmo: String,
    val parentGuid: String,
    val parents: List<Parent>,
    val type: String,
    val typeShort: String,
    val zip: Int
)