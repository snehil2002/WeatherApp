package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.navigation.navigation
import com.example.weatherapp.ui.theme.WeatherappTheme
import com.example.weatherapp.viewmodels.Dbviewmodel
import com.example.weatherapp.viewmodels.Mainviewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            WeatherappTheme {
                val mainviewmodel= hiltViewModel<Mainviewmodel>()
                val dbviewmodel= hiltViewModel<Dbviewmodel>()
                Myapp(mainviewmodel, dbviewmodel)
            }
        }
    }



}

@Composable
fun Myapp(mainviewmodel:Mainviewmodel= hiltViewModel(),dbviewmodel: Dbviewmodel){

    MaterialTheme(colors = lightThemeColors) {
        Surface(color = MaterialTheme.colors.background) {

            navigation(mainviewmodel, dbviewmodel)
        }
    }

}

private val lightThemeColors = lightColors(
    primary = Color.Black,
    background = Color.White
)


