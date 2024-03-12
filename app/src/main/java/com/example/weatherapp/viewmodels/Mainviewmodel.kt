package com.example.weatherapp.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.Weather
import com.example.weatherapp.dataorexception.Dataorexception
import com.example.weatherapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Mainviewmodel @Inject constructor(private val repo:Repo):ViewModel() {
 suspend fun getweatherdata(city:String,unit:String):Dataorexception<Weather>{
     return repo.getweatherdata(city=city, unit = unit)
 }
}