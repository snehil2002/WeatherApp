package com.example.weatherapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {

    //favourites

    @Query("SELECT * from Favouritedata")
    fun getfavs():Flow<List<Favouritedata>>

    @Query("SELECT * from Favouritedata where favcity=:favcity")
    suspend fun getfavbyid(favcity:String):Favouritedata

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertfav(favourite:Favouritedata)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatefav(favourite: Favouritedata)

    @Query("DELETE  from Favouritedata")
    suspend fun deleteall()

    @Delete
    suspend fun deletefav(favourite: Favouritedata)

    //settings

    @Query("SELECT * from Settingdata")
    fun getunits():Flow<List<Settingdata>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertunit(unitsetting:Settingdata)

    @Query("DELETE from Settingdata")
    suspend fun deleteallunits()

    @Delete
    suspend fun deleteunit(unitsetting: Settingdata)



}