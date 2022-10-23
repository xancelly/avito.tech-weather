package com.example.avitotechweather.presentation.fragments.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.data.repository.WeatherApiServiceImpl
import com.example.avitotechweather.domain.models.Weather
import com.example.avitotechweather.util.Constants.DEFAULT_LATITUDE
import com.example.avitotechweather.util.Constants.DEFAULT_LONGITUDE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(private val weatherApiServiceImpl: WeatherApiServiceImpl): ViewModel() {

    private val response = MutableLiveData<Weather>()

    val weatherResponse: LiveData<Weather>
        get() = response

    init {
        getWeather(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
    }

    private fun getWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        weatherApiServiceImpl.getWeather(lat = latitude, lon = longitude).let { resp ->
            if (resp.isSuccessful) {
                response.postValue(resp.body())
                Log.i("getWeather", resp.message())
            } else {
                Log.e("getWeather", resp.message())
            }
        }
    }
}