package com.example.avitotechweather.presentation.fragments.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.avitotechweather.R
import com.example.avitotechweather.data.repository.WeatherRepositoryImpl
import com.example.avitotechweather.databinding.FragmentWeatherBinding
import com.example.avitotechweather.domain.usecases.DateTimeConverter
import com.example.avitotechweather.util.Constants.condition
import com.example.avitotechweather.util.Constants.dayOfWeek
import com.example.avitotechweather.util.Constants.month
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private var weatherBinding: FragmentWeatherBinding? = null
    private val binding get() = weatherBinding!!
    private val viewModel: WeatherViewModel by viewModels()
    private val dateTimeConverter = DateTimeConverter()
    private lateinit var weatherAdapter: WeatherAdapter
    private val weatherRepository = WeatherRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        weatherBinding = FragmentWeatherBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataInHeader()
        loadDataInFields()
        loadDataInRecyclerView()
    }

    @SuppressLint("SetTextI18n")
    private fun loadDataInHeader() {
        viewModel.weatherResponse.observe(requireActivity()) { weather ->
            binding.apply {
                currentCityTextView.text = if (weather.geo_object.province.name == weather.geo_object.locality.name)
                                                if (weather.geo_object.district != null)
                                                    "${weather.geo_object.province.name}, ${weather.geo_object.district.name}"
                                                else
                                                    weather.geo_object.province.name
                                            else
                                                if (weather.geo_object.district != null)
                                                    "${weather.geo_object.province.name}, ${weather.geo_object.locality.name}, ${weather.geo_object.district.name}"
                                                else
                                                    "${weather.geo_object.province.name}, ${weather.geo_object.locality.name}"

                val currentDate = LocalDate.now()
                dayOfWeekTextView.text = "${dayOfWeek[currentDate.dayOfWeek.value]}"
                dateTextView.text = "${currentDate.dayOfMonth} ${month[currentDate.monthValue]}"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadDataInFields() {

        viewModel.weatherResponse.observe(viewLifecycleOwner) { weather ->
            binding.apply {
                //Change background
                timeOfDayTextView.text = dateTimeConverter.getTimeOfDay(weather.now_dt, weather.info.tzinfo.offset)
                temperatureTextView.text = if (weather.fact.temp > 0)
                                                "+${weather.fact.temp}°C"
                                            else
                                                "${weather.fact.temp}°C"
                weatherTextView.text = condition[weather.fact.condition]
                pressureTextView.text = "${weather.fact.pressure_mm} мм. рт. ст."
                windTextView.text = "ветер ${weather.fact.wind_speed} м/с"
                humidityTextView.text = "влажность ${weather.fact.humidity}%"
                nextTimeOfDayTextView.text = dateTimeConverter.getNextTimeOfDay(timeOfDayTextView.text.toString())
                when (nextTimeOfDayTextView.text) {
                    "Утро" -> {
                        nextTimeOfDayTemperatureTextView.text = if (weather.forecasts[0].parts.morning.temp_max > 0)
                                                                    "+${weather.forecasts[0].parts.morning.temp_max}°C"
                                                                else
                                                                    "${weather.forecasts[0].parts.morning.temp_max}°C"
                    }
                    "День" -> {
                        nextTimeOfDayTemperatureTextView.text = if (weather.forecasts[0].parts.day.temp_max > 0)
                                                                    "+${weather.forecasts[0].parts.day.temp_max}°C"
                                                                else
                                                                    "${weather.forecasts[0].parts.day.temp_max}°C"
                    }
                    "Вечер" -> {
                        nextTimeOfDayTemperatureTextView.text = if (weather.forecasts[0].parts.evening.temp_max > 0)
                                                                    "+${weather.forecasts[0].parts.evening.temp_max}°C"
                                                                else
                                                                    "${weather.forecasts[0].parts.evening.temp_max}°C"
                    }
                    "Ночь" -> {
                        nextTimeOfDayTemperatureTextView.text = if (weather.forecasts[0].parts.night.temp_max > 0)
                                                                    "+${weather.forecasts[0].parts.night.temp_max}°C"
                                                                else
                                                                    "${weather.forecasts[0].parts.night.temp_max}°C"
                    }
                }
            }
        }
    }

    private fun loadDataInRecyclerView() {
        weatherAdapter = WeatherAdapter()

        binding.miniWeatherWeekRecyclerView.apply {
            adapter = weatherAdapter
        }

        viewModel.weatherResponse.observe(requireActivity()) { weather ->
            weatherAdapter.forecastList = weather.forecasts
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        weatherBinding = null
    }
}