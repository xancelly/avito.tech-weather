package com.example.avitotechweather.di

import com.example.avitotechweather.data.api.KladrApi
import com.example.avitotechweather.data.api.WeatherApi
import com.example.avitotechweather.data.repository.KladrRepositoryImpl
import com.example.avitotechweather.data.repository.WeatherRepositoryImpl
import com.example.avitotechweather.domain.repository.KladrRepository
import com.example.avitotechweather.domain.repository.WeatherRepository
import com.example.avitotechweather.util.Constants.KLADR_BASE_URL
import com.example.avitotechweather.util.Constants.WEATHER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    @Named("Kladr")
    fun provideKldrApi(): KladrApi {
        return Retrofit.Builder()
            .baseUrl(KLADR_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KladrApi::class.java)
    }

    @Provides
    @Singleton
    fun provideKladrRepository(@Named("Kladr") kladrApi: KladrApi): KladrRepository {
        return KladrRepositoryImpl(kladrApi)
    }
}