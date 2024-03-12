package com.example.weatherapp.apidata

import com.example.weatherapp.appmodule.Appmodule
import com.example.weatherapp.data.Weather
import dagger.Component
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface Weatherapi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getweather(
        @Query("q")
        city:String,
        @Query("appid")
        appid:String=Constants.appid,
        @Query("units")
        units:String="metric"
    ):Weather
}