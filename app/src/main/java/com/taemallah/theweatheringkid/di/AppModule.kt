package com.taemallah.theweatheringkid.di

import android.app.Application
import android.content.Context
import com.taemallah.theweatheringkid.constants.Consts
import com.taemallah.theweatheringkid.mainScreen.data.api.WeatherApi
import com.taemallah.theweatheringkid.mainScreen.data.repository.WeatherPreferencesRepoImpl
import com.taemallah.theweatheringkid.mainScreen.data.repository.WeatherRepoImpl
import com.taemallah.theweatheringkid.mainScreen.domain.repository.WeatherPreferencesRepo
import com.taemallah.theweatheringkid.mainScreen.domain.repository.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi{
        return Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideWeatherRepo(api: WeatherApi):WeatherRepo{
        return WeatherRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideWeatherPreferencesRepo(application: Application):WeatherPreferencesRepo{
        return WeatherPreferencesRepoImpl(application.baseContext)
    }

}