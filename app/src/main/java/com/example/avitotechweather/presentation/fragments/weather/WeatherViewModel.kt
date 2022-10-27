package com.example.avitotechweather.presentation.fragments.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.domain.models.weather.Weather
import com.example.avitotechweather.domain.repository.WeatherRepository
import com.example.avitotechweather.util.Constants.DEFAULT_LATITUDE
import com.example.avitotechweather.util.Constants.DEFAULT_LONGITUDE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(private val weatherRepository: WeatherRepository): ViewModel() {

    private val response = MutableLiveData<Weather>()

    val weatherResponse: LiveData<Weather>
        get() = response

    init {
        getWeather(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
    }

    fun updateWeather(latitude: Double, longitude: Double) {
        getWeather(latitude, longitude)
    }

    private fun getWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        weatherRepository.getWeather(lat = latitude, lon = longitude).let { resp ->
            if (resp.isSuccessful) {
                response.postValue(resp.body())
                Log.i("getWeather", resp.message())
            } else {
                Log.e("getWeather", resp.message())
            }
        }
    }
}