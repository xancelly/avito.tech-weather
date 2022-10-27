package com.example.avitotechweather.domain.usecases

import android.content.Context
import android.view.View
import com.example.avitotechweather.R
import com.example.avitotechweather.util.Constants.CURRENT_DAYTIME

class DynamicBackgroundChange {
    fun changeBackground(view: View) {
        when (CURRENT_DAYTIME) {
            "Утро" -> {
                view.setBackgroundResource(R.drawable.morning_background)
            }
            "День" -> {
                view.setBackgroundResource(R.drawable.midday_background)
            }
            "Вечер" -> {
                view.setBackgroundResource(R.drawable.evening_background)
            }
            "Ночь" -> {
                view.setBackgroundResource(R.drawable.night_background)
            }
        }
    }
}