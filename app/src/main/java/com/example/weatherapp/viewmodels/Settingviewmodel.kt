package com.example.weatherapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.database.Settingdata
import com.example.weatherapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Settingviewmodel @Inject constructor(private  val repo:Repo):ViewModel() {
    private var _unitdata=MutableStateFlow<List<Settingdata>>(emptyList())
    var unitdata=_unitdata.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getallunits().distinctUntilChanged().collect(){
                _unitdata.value=it

            }
        }
    }

    fun insertunit(unitsetting:Settingdata){
        viewModelScope.launch { repo.insertunit(unitsetting) }
    }

    fun deleteunit(unitsetting:Settingdata){
        viewModelScope.launch{
            repo.deleteunit(unitsetting)
        }
    }

    fun deleteallunits(){
        viewModelScope.launch {
            repo.deleteallunits()
        }
    }

}