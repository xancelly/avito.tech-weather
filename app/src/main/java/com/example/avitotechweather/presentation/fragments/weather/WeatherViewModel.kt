package com.example.avitotechweather.presentation.fragments.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.data.repository.ApiServiceImpl
import com.example.avitotechweather.domain.models.Weather
import com.example.avitotechweather.util.Constants.DEFAULT_LATITUDE
import com.example.avitotechweather.util.Constants.DEFAULT_LONGITUDE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl): ViewModel() {

    private val response = MutableLiveData<Weather>()

    val weatherResponse: LiveData<Weather>
        get() = response

    init {
        getWeather(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
    }

    private fun getWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        apiServiceImpl.getWeather(lat = latitude, lon = longitude).let { resp ->
            if (resp.isSuccessful) {
                response.postValue(resp.body())
            } else {
                Log.e("getWeather", "getWeather() error: ${resp.message()}")
            }
        }
    }
}