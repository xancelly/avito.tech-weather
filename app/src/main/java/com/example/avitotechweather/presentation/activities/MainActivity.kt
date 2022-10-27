package com.example.avitotechweather.presentation.activities

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalTime
import java.util.Calendar


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
                getLocation()
            }
        }
    }

    private fun getLocation() {

        connectionLiveData.removeObservers(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 100)
            return
        }

        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if (it != null) {
                DEFAULT_LATITUDE = it.latitude
                DEFAULT_LONGITUDE = it.longitude
                showNextFragment()
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                Log.i("onRequestPermissionsResult", "Permissions are granted!")
            } else {
                Log.e("onRequestPermissionsResult", "Permissions are denied!")
            }
        }
        showNextFragment()
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