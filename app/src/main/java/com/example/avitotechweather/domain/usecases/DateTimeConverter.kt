package com.example.avitotechweather.domain.usecases

import com.example.avitotechweather.util.Constants.month
import com.example.avitotechweather.util.Constants.shortDayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DateTimeConverter {
    private fun convertStringToTime(currentDate: String): LocalTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(currentDate.replace("T", " ").substring(0, currentDate.length-8), formatter).toLocalTime()
    }

    fun convertStringToDate(currentDate: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(currentDate, formatter)
    }

    private fun addTimeZoneToTime(time: LocalTime, offset: Int): LocalTime {
        return time.plusHours(((offset / 3600).toLong()))
    }

    fun getDateFromString(date: String): String {
        val currentDate = convertStringToDate(date)
        return "${currentDate.dayOfMonth}"
    }

    fun getNameOfMonthFromString(date: String): String {
        val currentDate = convertStringToDate(date)
        return "${month[currentDate.monthValue]}"
    }

    fun getShortDayOfWeekFromString(date: String): String {
        val currentDate = convertStringToDate(date).dayOfWeek.value
        return shortDayOfWeek[currentDate].toString()
    }

    fun getNextTimeOfDay(daytime: String) : String {
        return when (daytime) {
            "Утро" -> {
                return "День"
            }
            "День" -> {
                return "Вечер"
            }
            "Вечер" -> {
                return "Ночь"
            }
            "Ночь" -> {
                return "Утро"
            }
            else -> {return "ERROR"}
        }
    }

    fun getTimeOfDay(time: String, offset: Int): String {
        return when (addTimeZoneToTime(convertStringToTime(time), offset)) {
            in LocalTime.of(5,0,0)..LocalTime.of(11, 59,59) -> {
                "Утро"
            }
            in LocalTime.of(12, 0,0)..LocalTime.of(16, 59,59) -> {
                "День"
            }
            in LocalTime.of(17, 0,0)..LocalTime.of(21, 59,59) -> {
                "Вечер"
            }
            else -> {
                "Ночь"
            }
        }
    }

    fun getTimeOfDay(time: LocalTime): String {
        return when (time) {
            in LocalTime.of(5,0,0)..LocalTime.of(11, 59,59) -> {
                "Утро"
            }
            in LocalTime.of(12, 0,0)..LocalTime.of(16, 59,59) -> {
                "День"
            }
            in LocalTime.of(17, 0,0)..LocalTime.of(21, 59,59) -> {
                "Вечер"
            }
            else -> {
                "Ночь"
            }
        }
    }
}