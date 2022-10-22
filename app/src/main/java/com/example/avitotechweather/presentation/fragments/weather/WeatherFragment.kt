package com.example.avitotechweather.presentation.fragments.weather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.avitotechweather.databinding.FragmentWeatherBinding
import com.example.avitotechweather.domain.usecases.GetDaytimeFromTime
import com.example.avitotechweather.util.Constants.condition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private var fragmentBinding: FragmentWeatherBinding? = null
    private val binding get() = fragmentBinding!!
    private val viewModel: WeatherViewModel by viewModels()
    private val getDaytimeFromTime = GetDaytimeFromTime()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentBinding = FragmentWeatherBinding.inflate(inflater, container, false)

        viewModel.weatherResponse.observe(viewLifecycleOwner) { weather ->
            binding.apply {
                timeOfDayTextView.text = getDaytimeFromTime.getTimeOfDay(weather.now_dt)
                temperatureTextView.text = "${weather.fact.temp}°C"
                weatherTextView.text = condition[weather.fact.condition]
                pressureTextView.text = "${weather.fact.pressure_mm} мм. рт. ст."
                windTextView.text = "ветер ${weather.fact.wind_speed} м/с"
                humidityTextView.text = "влажность ${weather.fact.humidity}%"
                nextTimeOfDayTextView.text = getDaytimeFromTime.getNextTimeOfDay(timeOfDayTextView.text.toString())
                nextTimeOfDayTemperatureTextView.text = "${weather.forecasts[0].parts.day.temp_max}°C"
            }
        }

        return binding.root
    }
}