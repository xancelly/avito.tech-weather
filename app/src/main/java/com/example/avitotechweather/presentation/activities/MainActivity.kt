package com.example.avitotechweather.presentation.activities

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.avitotechweather.R
import com.example.avitotechweather.domain.usecases.GetUserGeoPosition
import com.example.avitotechweather.presentation.fragments.weather.WeatherFragment
import com.example.avitotechweather.util.Constants.DEFAULT_LATITUDE
import com.example.avitotechweather.util.Constants.DEFAULT_LONGITUDE
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_COARSE_LOCATION), 1)
        }
        showNextFragment()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("onRequestPermissionsResult", "Permissions granted!")
            } else {
                Log.e("onRequestPermissionsResult", "Permissions denied!")
            }
        }
        showNextFragment()
    }

    private fun showNextFragment() {
        val weatherFragment = WeatherFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, weatherFragment).commit()
    }
}