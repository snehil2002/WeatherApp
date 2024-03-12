package com.example.weatherapp.screens

import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.NavScreens
import kotlinx.coroutines.delay

@Composable fun Splashscreen(navcontroller: NavController){
    var scale=remember{
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue =250f,
            animationSpec = tween(easing = {OvershootInterpolator(6f).getInterpolation(it)}, durationMillis = 600))
        delay(800L)
        navcontroller.navigate("${NavScreens.Homescreen.name}/bareilly")
    }
    
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(modifier = Modifier.size(scale.value.dp), shape = CircleShape,
            border = BorderStroke(width = 3.dp, color = Color.LightGray)
            ) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id =com.example.weatherapp.R.drawable.sun), 
                        contentDescription ="sun", modifier = Modifier.size(100.dp) )
                    Text(text = "Find the Sun?", fontSize = 25.sp, color = Color.LightGray)
                }
            }
            
        }
        

    }
}