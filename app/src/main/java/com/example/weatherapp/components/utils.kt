package com.example.weatherapp.components

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun timestamptodate(times:Int):String{
    val date= Date(times.toLong()*1000)
    return SimpleDateFormat("EEE, MMM d").format(date)
}

fun timestamptotime(times:Int):String{
    val date=Date(times.toLong()*1000)
    return SimpleDateFormat("hh:mm aaa").format(date)
}

fun toastmessage(context: Context, message:String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}
