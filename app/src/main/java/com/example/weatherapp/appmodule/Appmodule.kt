package com.example.weatherapp.appmodule

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.apidata.Constants
import com.example.weatherapp.apidata.Weatherapi
import com.example.weatherapp.database.Dao
import com.example.weatherapp.database.Favouritedata
import com.example.weatherapp.database.Roomdb
import com.example.weatherapp.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Appmodule {

    @Singleton
    @Provides
    fun getapi():Weatherapi{
        return Retrofit.Builder().baseUrl(Constants.baseurl).
        addConverterFactory(GsonConverterFactory.create()).build().create(Weatherapi::class.java)
    }



    @Singleton
    @Provides
    fun getdao(roomdb: Roomdb):Dao{
       return roomdb.getdao()

    }

    @Singleton
    @Provides
    fun getdb(@ApplicationContext context: Context):Roomdb{
        return Room.databaseBuilder(context = context,
        klass = Roomdb::class.java, name = "roomdb").fallbackToDestructiveMigration().build()
    }
}