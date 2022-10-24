package com.example.avitotechweather.presentation.fragments.weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.avitotechweather.databinding.MiniWeatherWeekLayoutBinding
import com.example.avitotechweather.domain.models.Forecast
import com.example.avitotechweather.domain.usecases.DateTimeConverter
import com.example.avitotechweather.util.Constants.condition

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(val binding: MiniWeatherWeekLayoutBinding): RecyclerView.ViewHolder(binding.root)

    private val dateTimeConverter = DateTimeConverter()

    private val diffCallback = object: DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var forecastList: List<Forecast>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(MiniWeatherWeekLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentForecast= forecastList[position]
        holder.binding.apply {
            dateTextView.text = "${dateTimeConverter.getDateAndNameOfMonthFromString(currentForecast.date)}, "
            dayOfWeekTextView.text = "${dateTimeConverter.getShortDayOfWeekFromString(currentForecast.date)}, "
            temperatureTextView.text = if (currentForecast.parts.day.temp_max > 0) "+${currentForecast.parts.day.temp_max}°C" else "${currentForecast.parts.day.temp_max}°C"
            weatherTextView.text = condition[currentForecast.parts.day.condition]
        }

/*        holder.itemView.setOnClickListener { view ->
            val direction = WeatherFragmentDirections.actionWeatherFragmentToDetailWeatherFragment(weather = weatherRepositoryImpl.getWeather())
            view.findNavController().navigate(direction)
        }*/
    }

    override fun getItemCount() = forecastList.size

}