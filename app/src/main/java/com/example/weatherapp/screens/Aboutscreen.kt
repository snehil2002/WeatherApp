package com.example.weatherapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.components.topbarw

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Aboutscreen(navcontroller: NavController){
    Scaffold(topBar = { topbarw(title ="About" , ismainscreen = false, elevation = 10.dp, navcontroller =navcontroller,backbuttonclick = {navcontroller.popBackStack()} ) }) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "Weather App", modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Text(text = "Version=1.0.1", fontWeight = FontWeight.Bold,
            fontSize = 20.sp)


        }

    }
}