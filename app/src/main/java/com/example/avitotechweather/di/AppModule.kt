package com.example.avitotechweather.di

import com.example.avitotechweather.data.api.GeocoderApi
import com.example.avitotechweather.data.api.WeatherApi
import com.example.avitotechweather.data.repository.GeocoderRepositoryImpl
import com.example.avitotechweather.data.repository.WeatherRepositoryImpl
import com.example.avitotechweather.domain.repository.GeocoderRepository
import com.example.avitotechweather.domain.repository.WeatherRepository
import com.example.avitotechweather.util.Constants.GEOCODER_BASE_URL
import com.example.avitotechweather.util.Constants.WEATHER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("Weather")
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(@Named("Weather") weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }

    @Provides
    @Singleton
    @Named("Geocoder")
    fun provideKldrApi(): GeocoderApi {
        return Retrofit.Builder()
            .baseUrl(GEOCODER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeocoderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideKladrRepository(@Named("Geocoder") geocoderApi: GeocoderApi): GeocoderRepository {
        return GeocoderRepositoryImpl(geocoderApi)
    }
}