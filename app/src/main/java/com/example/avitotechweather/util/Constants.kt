package com.example.avitotechweather.util

object Constants {
    const val BASE_URL = "https://api.weather.yandex.ru/v2/"
    const val API_KEY = "6985a2e7-e1b8-47ca-bab9-b24a3d2cb079"

    var DEFAULT_LATITUDE: Double = 55.751244
    var DEFAULT_LONGITUDE: Double = 37.618423

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

    val daytime: Map<String, String> = mapOf("" to "День", "n" to "Ночь")

}