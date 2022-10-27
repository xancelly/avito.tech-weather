package com.example.avitotechweather.presentation.fragments.detailweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.avitotechweather.databinding.FragmentDetailWeatherBinding
import com.example.avitotechweather.domain.models.weather.Weather
import com.example.avitotechweather.domain.usecases.DynamicBackgroundChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailWeatherFragment : Fragment() {

    private var detailBinding: FragmentDetailWeatherBinding? = null
    private val binding get() = detailBinding!!
    private val weatherArgs: DetailWeatherFragmentArgs by navArgs()
    private lateinit var weather: Weather
    private lateinit var detailWeatherAdapter: DetailWeatherAdapter
    private var dynamicBackgroundChange: DynamicBackgroundChange = DynamicBackgroundChange()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = FragmentDetailWeatherBinding.inflate(inflater, container, false)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val direction = DetailWeatherFragmentDirections.actionDetailWeatherFragmentToWeatherFragment()
                findNavController().navigate(direction)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        dynamicBackgroundChange.changeBackground(binding.root)
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