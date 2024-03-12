package com.example.weatherapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.components.toastmessage
import com.example.weatherapp.components.topbarw
import com.example.weatherapp.navigation.NavScreens
import com.example.weatherapp.viewmodels.Dbviewmodel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable fun Favouritescreen(navcontroller:NavController,dbviewmodel: Dbviewmodel){
    val dcontext= LocalContext.current
    Scaffold(topBar = { topbarw(title ="Favourites" , ismainscreen = false, elevation = 10.dp, navcontroller =navcontroller, backbuttonclick = {navcontroller.popBackStack()} )}) {
var favdata=dbviewmodel.favdata.collectAsState()
        if (favdata.value.isEmpty()){
            Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                Text(text = "No Favourites Yet !!!!", fontSize = 30.sp,
                fontWeight = FontWeight.Bold)

            }
        }
        else{
        
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(favdata.value){
                
                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 3.dp)
                    .height(50.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 30.dp,
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    ),
                color = Color(0xFF41DDCE)
                ) {
                    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(text = it.favcity, fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp, modifier = Modifier.clickable {
                            navcontroller.navigate("${NavScreens.Homescreen.name}/${it.favcity.lowercase()}")
                            })
                        Surface(shape = CircleShape, color = Color.LightGray, modifier = Modifier
                            .width(40.dp)
                            .height(30.dp)) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                            Text(text = it.favcountry, fontWeight = FontWeight.SemiBold)}
                            
                        }
                        Icon(imageVector = Icons.Default.Delete, contentDescription ="",tint=Color(
                            0xFFF86A6A
                        ), modifier = Modifier.clickable {
                            dbviewmodel.deletefav(it)
                            toastmessage(dcontext,"Removed from Favourites")
                        }
                        )

                        
                    }
                }
            }
        }}
            
                
            


    }
}