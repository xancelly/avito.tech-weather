package com.example.avitotechweather.presentation.fragments.detailweather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.avitotechweather.databinding.FullWeatherWeekLayoutBinding
import com.example.avitotechweather.domain.models.Forecast
import com.example.avitotechweather.domain.usecases.DateTimeConverter
import com.example.avitotechweather.presentation.fragments.weather.WeatherAdapter
import com.example.avitotechweather.util.Constants.condition
import com.example.avitotechweather.util.Constants.dayOfWeek
import java.time.LocalDate

class DetailWeatherAdapter: RecyclerView.Adapter<DetailWeatherAdapter.DetailWeatherViewHolder>() {
    class DetailWeatherViewHolder(val binding: FullWeatherWeekLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailWeatherViewHolder {
        return DetailWeatherViewHolder(FullWeatherWeekLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailWeatherViewHolder, position: Int) {
        val currentForecast = forecastList[position]
        holder.binding.apply {
            val currentDate = dateTimeConverter.convertStringToDate(currentDate = currentForecast.date)
            dayOfWeekTextView.text = dayOfWeek[currentDate.dayOfWeek.value]
            dateTextView.text = "${dateTimeConverter.getDateFromString(currentForecast.date)} ${dateTimeConverter.getNameOfMonthFromString(currentForecast.date)}"
            dayTemperatureTextView.text = if (currentForecast.parts.day.temp_max > 0) "+${currentForecast.parts.day.temp_max}°C" else "${currentForecast.parts.day.temp_max}"
            dayWeatherTextView.text = condition[currentForecast.parts.day.condition]
        }
    }

    override fun getItemCount(): Int = forecastList.size
}