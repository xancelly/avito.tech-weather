package com.example.avitotechweather.presentation.activities

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.avitotechweather.R
import com.example.avitotechweather.databinding.ActivityMainBinding
import com.example.avitotechweather.domain.usecases.ConnectionLiveData
import com.example.avitotechweather.domain.usecases.DateTimeConverter
import com.example.avitotechweather.domain.usecases.DynamicBackgroundChange
import com.example.avitotechweather.presentation.fragments.launch.LaunchFragmentDirections
import com.example.avitotechweather.util.Constants.CURRENT_DAYTIME
import com.example.avitotechweather.util.Constants.DEFAULT_LATITUDE
import com.example.avitotechweather.util.Constants.DEFAULT_LONGITUDE
import com.example.avitotechweather.util.Constants.PERMISSION_REQUEST_ACCESS_LOCATION
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalTime


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var connectionLiveData: ConnectionLiveData
    private var mainBinding: ActivityMainBinding? = null
    private val binding get() = mainBinding!!
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var dateTimeConverter: DateTimeConverter
    private var dynamicBackgroundChange: DynamicBackgroundChange = DynamicBackgroundChange()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateTimeConverter = DateTimeConverter()
        val currentTime: LocalTime = LocalTime.now()
        CURRENT_DAYTIME = dateTimeConverter.getTimeOfDay(currentTime)
        dynamicBackgroundChange.changeBackground(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostFragment.host

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) { isConnected ->
            isConnected?.let {
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                getCurrentLocation()
            }
        }
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun getCurrentLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location != null) {
                        connectionLiveData.removeObservers(this)

                        DEFAULT_LATITUDE = location.latitude
                        DEFAULT_LONGITUDE = location.longitude

                        showNextFragment()
                    }
                }
            } else {
                Toast.makeText(this, "Необходимо включить геолокацию!", Toast.LENGTH_LONG).show()
            }
        } else {
            requestPermission()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            if (grantResults.isNotEmpty() && (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                getCurrentLocation()
                Log.i("onRequestPermissionsResult", "Permission is granted!")
            } else {
                Log.e("onRequestPermissionsResult", "Permission is denied!")
            }
        }
    }


    private fun showNextFragment() {
        Thread.sleep(500)
        val direction = LaunchFragmentDirections.actionLaunchFragmentToWeatherFragment()
        val navController: NavController = navHostFragment.navController
        navController.navigate(direction)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }
}