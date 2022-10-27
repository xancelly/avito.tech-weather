package com.example.avitotechweather

import android.app.Application
import com.example.avitotechweather.util.Constants.MAP_API_KEY
import dagger.hilt.android.HiltAndroidApp
import com.yandex.mapkit.MapKitFactory

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(MAP_API_KEY)
    }
}