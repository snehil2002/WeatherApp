package com.example.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favouritedata::class,Settingdata::class], version = 2, exportSchema = false)
abstract class Roomdb:RoomDatabase() {
    abstract fun getdao():Dao
}