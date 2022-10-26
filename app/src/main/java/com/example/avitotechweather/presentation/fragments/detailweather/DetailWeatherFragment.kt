package com.example.avitotechweather.presentation.fragments.detailweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.avitotechweather.R
import com.example.avitotechweather.databinding.FragmentDetailWeatherBinding
import com.example.avitotechweather.domain.models.Weather
import com.example.avitotechweather.domain.usecases.DateTimeConverter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailWeatherFragment : Fragment() {

    private var detailBinding: FragmentDetailWeatherBinding? = null
    private val binding get() = detailBinding!!
    private val weatherArgs: DetailWeatherFragmentArgs by navArgs()
    private lateinit var weather: Weather
    private lateinit var detailWeatherAdapter: DetailWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = FragmentDetailWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weather = weatherArgs.weather

        getDataFromArgs()
    }

    private fun getDataFromArgs() {
        binding.apply {
            detailWeatherAdapter = DetailWeatherAdapter()
            binding.weekWeatherRecyclerView.apply {
                adapter = detailWeatherAdapter
            }

            detailWeatherAdapter.forecastList = weather.forecasts
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        detailBinding = null

    }
}