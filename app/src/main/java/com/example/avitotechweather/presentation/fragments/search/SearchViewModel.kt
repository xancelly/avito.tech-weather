package com.example.avitotechweather.presentation.fragments.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avitotechweather.domain.repository.GeocoderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject
constructor(private val geocoderRepository: GeocoderRepository): ViewModel() {
    private val response = String

    val geocoderResponse: String
        get() = response.toString()

    private fun getCityPosition(search: String) = viewModelScope.launch {
        geocoderRepository.getCityPosition(search).let { resp ->
            if (resp.isSuccessful) {
                response.toString()
                Log.i("getCityPosition", resp.message())
            } else {
                Log.e("getCityPosition", resp.message())
            }
        }
    }
}