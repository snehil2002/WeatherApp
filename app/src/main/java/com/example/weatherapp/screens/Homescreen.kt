package com.example.weatherapp.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.weatherapp.components.*
import com.example.weatherapp.data.Weather
import com.example.weatherapp.database.Favouritedata
import com.example.weatherapp.dataorexception.Dataorexception
import com.example.weatherapp.navigation.NavScreens
import com.example.weatherapp.viewmodels.Dbviewmodel
import com.example.weatherapp.viewmodels.Mainviewmodel
import com.example.weatherapp.viewmodels.Settingviewmodel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Homescreen(navcontroller: NavController,mainviewmodel:Mainviewmodel,city:String,
dbviewmodel: Dbviewmodel,settingviewmodel:Settingviewmodel){
    var unitvalue= remember {
        mutableStateOf("metric")
    }
    var unitdata=settingviewmodel.unitdata.collectAsState()
    if (unitdata.value.isNotEmpty() && unitdata.value[0].unit=="imperial"){
        unitvalue.value="imperial"
    }
    else{
        unitvalue.value="metric"
    }
    var unitsymbol=remember{
        mutableStateOf("C")
    }
    if(unitvalue.value=="imperial"){
        unitsymbol.value="F"

    }else{
        unitsymbol.value="C"

    }
    var favdata=dbviewmodel.favdata.collectAsState()
    val mcontext= LocalContext.current
    var weatheralldata = produceState(initialValue = Dataorexception<Weather>(loading = true) ){
            value=mainviewmodel.getweatherdata(city=city, unit = unitvalue.value)
        }.value
    var weatherdata=weatheralldata.data



    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        if(weatheralldata.loading==true){
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            CircularProgressIndicator()}
        }
        else {
            var showdropdown= remember {
                mutableStateOf(false)

            }
            var showfavbutton= remember {
                mutableStateOf(true)
            }


            if(Favouritedata(favcity = weatherdata!!.city.name, favcountry = weatherdata.city.country) in favdata.value){
                showfavbutton.value=false
            }
            else{
                showfavbutton.value=true
            }
            val routelist= listOf(NavScreens.Favouritescreen.name,NavScreens.Settingscreen.name,NavScreens.Aboutscreen.name)
            val dropitems= listOf("Favourites","Setting","About")
            val dropicons=listOf(Icons.Default.Favorite,Icons.Default.Settings,Icons.Default.Info)
            Scaffold(topBar = {topbarw(title = "${weatherdata!!.city.name},${weatherdata.city.country}", ismainscreen = true, elevation = 10.dp, navcontroller = navcontroller,
            searchbuttonclick = {navcontroller.navigate(NavScreens.Searchscreen.name)},
            dropdownclick = {showdropdown.value=true}, favbuttonclick = {
                dbviewmodel.insertfav(Favouritedata(favcity = weatherdata.city.name, favcountry = weatherdata.city.country))
                toastmessage(mcontext,"Added to Favourites")
                }, showfavbutton = showfavbutton)}) {

                if(showdropdown.value==true){
                    dropdown(showdropdown = showdropdown, navcontoller =navcontroller, items = dropitems,
                    itemicons = dropicons,routelist=routelist)}

                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = timestamptodate(weatherdata!!.list[0].dt), fontWeight = FontWeight.Bold, fontSize = 17.sp,
                    modifier = Modifier.padding(5.dp))
                    Surface(modifier = Modifier.size(170.dp), shape = CircleShape,
                    color = Color(0xFFECBD11)
                    ) {
                        Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                            Image(painter = rememberImagePainter("https://openweathermap.org/img/wn/${weatherdata.list[0].weather[0].icon}.png"), contentDescription ="icon",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(7.dp))
                            Text(text = "${weatherdata.list[0].temp.day.toInt()}°${unitsymbol.value}", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                            Text(text = weatherdata.list[0].weather[0].main, fontStyle = FontStyle.Italic, fontSize = 17.sp)

                        }

                    }
                    humiditypresswind(humidity = "${weatherdata.list[0].humidity}%", press ="${weatherdata.list[0].pressure} psi" , wind = "${weatherdata.list[0].speed} m/s")
Divider(modifier = Modifier.padding(2.dp))
sunriseset(sunrise = weatherdata.list[0].sunrise, sunset =weatherdata.list[0].sunset )
                    Text(text = "This Week", fontSize = 23.sp,
                    fontWeight = FontWeight.Bold)
                    Surface(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                        shape = RoundedCornerShape(10.dp),
                        color = Color.LightGray
                    ) {
                        LazyColumn(){
                            items(weatherdata.list){
                                weekrow(listitem = it)
                                
                            }
                        }

                    }

                }


            }
            }
    }
}