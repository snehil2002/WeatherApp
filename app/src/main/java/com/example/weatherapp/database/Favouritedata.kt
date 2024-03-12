package com.example.weatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Favouritedata(
    @PrimaryKey
    @NotNull
    @ColumnInfo
    var favcity:String,

    @ColumnInfo
    var favcountry:String

)
