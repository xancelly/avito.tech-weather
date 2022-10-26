package com.example.avitotechweather.util

import com.example.avitotechweather.domain.models.Weather

object Constants {
    //Yandex.Weather API
    const val WEATHER_BASE_URL = "https://api.weather.yandex.ru/"
    const val WEATHER_API_KEY = "6985a2e7-e1b8-47ca-bab9-b24a3d2cb079"

    //MapKit
    const val MAP_API_KEY = "4c0b0b0b-4f72-404a-b127-d90c371613e5"

    //KLADR-API
    const val KLADR_BASE_URL = "https://kladr-api.ru/"

    var DEFAULT_LATITUDE: Double = 55.152621
    var DEFAULT_LONGITUDE: Double = 37.460476

    var WEATHER: Weather? = null

    val dayOfWeek: Map<Int, String> = mapOf(
        1 to "Понедельник",
        2 to "Вторник",
        3 to "Среда",
        4 to "Четверг",
        5 to "Пятница",
        6 to "Суббота",
        7 to "Воскресенье"
    )

    val shortDayOfWeek: Map<Int, String> = mapOf(
        1 to "пн",
        2 to "вт",
        3 to "ср",
        4 to "чт",
        5 to "пт",
        6 to "сб",
        7 to "вс"
    )

    val month: Map<Int, String> = mapOf(
        1 to "января",
        2 to "февраля",
        3 to "марта",
        4 to "апреля",
        5 to "мая",
        6 to "июня",
        7 to "июля",
        8 to "августа",
        9 to "сентября",
        10 to "октября",
        11 to "ноября",
        12 to "декабря"
    )

    val condition: Map<String, String> = mapOf(
        "clear" to "ясно",
        "partly-cloudy" to "малооблачно",
        "cloudy" to "облачно с прояснениями",
        "overcast" to "пасмурно",
        "drizzle" to "морось",
        "light-rain" to "небольшой дождь",
        "rain" to "дождь",
        "moderate-rain" to "умеренно сильный дождь",
        "heavy-rain" to "сильный дождь",
        "continuous-heavy-rain" to "длительный сильный дождь",
        "showers" to "ливень",
        "wet-snow" to "дождь со снегом",
        "light-snow" to "небольшой снег",
        "snow" to "снег",
        "snow-showers" to "снегопад",
        "hail" to "град",
        "thunderstorm" to "гроза",
        "thunderstorm-with-rain" to "дождь с грозой",
        "thunderstorm-with-hail" to "гроза с градом"
    )
}