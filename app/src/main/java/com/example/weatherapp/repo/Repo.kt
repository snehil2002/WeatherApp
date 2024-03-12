package com.example.weatherapp.repo

import android.util.Log
import com.example.weatherapp.apidata.Weatherapi
import com.example.weatherapp.data.Weather
import com.example.weatherapp.database.Dao
import com.example.weatherapp.database.Favouritedata
import com.example.weatherapp.database.Settingdata
import com.example.weatherapp.dataorexception.Dataorexception
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repo @Inject constructor(private val api:Weatherapi,private val dao: Dao) {
 var weatherdata=Dataorexception<Weather>()
    suspend fun getweatherdata(city:String,unit:String):Dataorexception<Weather>{
        try{
            weatherdata.data=api.getweather(city = city, units = unit)
            weatherdata.loading=false

        }
        catch (e:Exception){
            Log.d("Exceeption", "getweatherdata: $e ")
        }
        return weatherdata
    }

     fun getallfav(): Flow<List<Favouritedata>> {
        return dao.getfavs()
    }
    suspend fun insertfav(favourite: Favouritedata){
        dao.insertfav(favourite)
    }
    suspend fun deletefav(favourite: Favouritedata){
        dao.deletefav(favourite)
    }

    fun getallunits():Flow<List<Settingdata>>{
        return dao.getunits()
    }

    suspend fun insertunit(unitsetting:Settingdata){
        dao.insertunit(unitsetting)
    }
    suspend fun deleteunit(unitsetting:Settingdata){
        dao.deleteunit(unitsetting)
    }
    suspend fun deleteallunits(){
        dao.deleteallunits()
    }

}