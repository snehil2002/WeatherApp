package com.example.weatherapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.components.topbarw
import com.example.weatherapp.database.Settingdata
import com.example.weatherapp.viewmodels.Settingviewmodel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Settingscreen(navcontroller: NavController,settingviewmodel: Settingviewmodel){
    var unitdata=settingviewmodel.unitdata.collectAsState()
    var togglestate=remember{
        mutableStateOf( if (unitdata.value.isNotEmpty() && unitdata.value[0].unit=="imperial"){
            false
        }else{
            true
        })
    }
    var togglevalue= remember {
        mutableStateOf("metric")
    }

    if(togglestate.value==false){
        togglevalue.value="imperial"
    }
    else{
        togglevalue.value="metric"
    }
    Scaffold(topBar = { topbarw(title ="Setting" , ismainscreen = false, elevation = 10.dp, navcontroller =navcontroller ,backbuttonclick = {navcontroller.popBackStack()}) }) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Change Unit of Measurement", fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
            IconToggleButton(checked =togglestate.value , onCheckedChange ={
                togglestate.value=!togglestate.value
            } ) {
                Surface(modifier = Modifier
                    .width(150.dp)
                    .height(60.dp)
                    .padding(10.dp), color = Color(0xFFF725E1),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,) {


                    Text(text = if(togglestate.value==true){
                        "Celcius °C"
                    }else{"Farenheit °F"}, color = Color.White,
                    fontWeight = FontWeight.Bold, fontSize = 20.sp)}

                }
                
            }
            Button(onClick = { settingviewmodel.deleteallunits()
                settingviewmodel.insertunit(Settingdata(togglevalue.value)) }, shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFDA062))) {
                Text(text = "Save", color = Color.White)
                
            }
            
        }

    }
}