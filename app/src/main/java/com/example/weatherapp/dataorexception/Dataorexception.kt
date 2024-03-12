package com.example.weatherapp.dataorexception

data class Dataorexception<T>(
    var data:T?=null,
    var loading:Boolean?=null,
    var exception:Exception?=null

)