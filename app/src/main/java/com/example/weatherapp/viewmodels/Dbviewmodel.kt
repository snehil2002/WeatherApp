package com.example.weatherapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.database.Favouritedata
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
class Dbviewmodel @Inject constructor(private val repo: Repo):ViewModel(){
   private var _favdata=MutableStateFlow<List<Favouritedata>>(emptyList())
    var favdata=_favdata.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getallfav().distinctUntilChanged().collect(){
                _favdata.value=it
            }
        }
    }
     fun insertfav(favourite:Favouritedata){
        viewModelScope.launch (){
            repo.insertfav(favourite)
        }
    }
    fun deletefav(favourite:Favouritedata){
        viewModelScope.launch{
            repo.deletefav(favourite)
        }
    }


}