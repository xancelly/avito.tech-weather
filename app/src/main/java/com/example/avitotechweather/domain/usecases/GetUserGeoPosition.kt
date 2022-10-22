package com.example.avitotechweather.domain.usecases

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient

class GetUserGeoPosition {
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    fun getPosition(): Boolean {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if (it == null) {
                Log.e("fusedLocationProviderClient", "Unable to get user location")
            } else it.apply {
                latitude = it.latitude
                longitude = it.longitude
            }
        }

        return !(latitude != 0.0 && longitude != 0.0)
    }

    fun getLatitude(): Double {
        return latitude
    }

    fun getLongitude(): Double {
        return longitude
    }
}