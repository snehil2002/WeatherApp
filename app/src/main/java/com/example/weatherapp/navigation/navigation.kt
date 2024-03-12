package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.screens.*
import com.example.weatherapp.viewmodels.Dbviewmodel
import com.example.weatherapp.viewmodels.Mainviewmodel
import com.example.weatherapp.viewmodels.Settingviewmodel

@Composable fun navigation(mainviewmodel: Mainviewmodel,dbviewmodel: Dbviewmodel){
    val settingviewmodel= hiltViewModel<Settingviewmodel>()
    val navcontroller= rememberNavController()
    NavHost(navController = navcontroller, startDestination = NavScreens.Spashscreen.name){
        composable(NavScreens.Spashscreen.name){
            Splashscreen(navcontroller = navcontroller)
            
        }
        composable("${NavScreens.Homescreen.name}/{city}", arguments = listOf( navArgument(name = "city"){
            type= NavType.StringType
        })){
            val city= it.arguments!!.getString("city")
            if (city != null) {
                Homescreen(navcontroller = navcontroller,mainviewmodel,city=city,dbviewmodel=dbviewmodel,
                settingviewmodel=settingviewmodel)
            }
        }
        composable(NavScreens.Searchscreen.name){
            Searchscreen(navcontroller = navcontroller)
        }
        composable(NavScreens.Favouritescreen.name){
            Favouritescreen(navcontroller = navcontroller,dbviewmodel=dbviewmodel)
        }
        composable(NavScreens.Settingscreen.name){
            Settingscreen(navcontroller = navcontroller,settingviewmodel=settingviewmodel)
        }
        composable(NavScreens.Aboutscreen.name){
            Aboutscreen(navcontroller = navcontroller)
        }
        
    }
}
