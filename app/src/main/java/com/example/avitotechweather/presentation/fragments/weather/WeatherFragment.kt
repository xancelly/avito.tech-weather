package com.example.avitotechweather.presentation.fragments.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.avitotechweather.R
import com.example.avitotechweather.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private var fragmentBinding: FragmentWeatherBinding? = null
    private val binding get() = fragmentBinding!!
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentBinding = FragmentWeatherBinding.inflate(inflater, container, false)

        viewModel.weatherResponse.observe(viewLifecycleOwner) { weather ->
            binding.apply {
                timeOfDayTextView.text = weather.geo_object.province.name
                temperatureTextView.text = weather.fact.temp.toString()
                weatherTextView.text = weather.fact.condition
            }
        }

        return binding.root
    }
}