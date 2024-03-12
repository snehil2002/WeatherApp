package com.example.weatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Settingdata (
    @NotNull
    @PrimaryKey
    @ColumnInfo
    val unit:String
        )