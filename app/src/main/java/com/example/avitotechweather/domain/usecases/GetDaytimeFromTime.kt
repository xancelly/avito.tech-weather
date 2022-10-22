package com.example.avitotechweather.domain.usecases

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class GetDaytimeFromTime {
    private fun getConverterTime(currentTime: String): LocalTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(currentTime.replace("T", " ").substring(0, currentTime.length-8), formatter).toLocalTime()
    }

    fun getTimeOfDay(currentTime: String): String {
        return when (getConverterTime(currentTime)) {
            in LocalTime.of(5,0,0)..LocalTime.of(11, 59,59) -> {
                "Утро"
            }
            in LocalTime.of(12, 0,0)..LocalTime.of(16, 59,59) -> {
                "День"
            }
            in LocalTime.of(17, 0,0)..LocalTime.of(21, 59,59) -> {
                "Вечер"
            }
            in  LocalTime.of(22, 0,0)..LocalTime.of(4, 59,59) -> {
                "Ночь"
            }
            else -> {
                "error"
            }
        }
    }

    fun getNextTimeOfDay(daytime: String) : String {
        return when (daytime) {
            "Утро", "День" -> {
                "Ночь"
            }
            "Вечер", "Ночь" -> {
                "День"
            }
            else -> {
                "error"
            }
        }
    }
}