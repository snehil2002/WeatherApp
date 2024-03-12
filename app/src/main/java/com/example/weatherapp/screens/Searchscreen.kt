package com.example.weatherapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.components.topbarw
import com.example.weatherapp.navigation.NavScreens


@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable fun Searchscreen(navcontroller: NavController){
    var searchvalue=remember{ mutableStateOf("") }
    val keyboardconttroller=LocalSoftwareKeyboardController.current
    Scaffold(topBar = { topbarw(title = "Search", ismainscreen = false, elevation = 10.dp, navcontroller =navcontroller, backbuttonclick = {navcontroller.popBackStack()} )}){
        OutlinedTextField(value = searchvalue.value, onValueChange ={
            searchvalue.value=it
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), singleLine = true, shape = RoundedCornerShape(10.dp),
        label = { Text(text = "Enter City")},
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        , keyboardActions = KeyboardActions(onSearch = {
            if(searchvalue.value.isNotEmpty()){
                navcontroller.navigate("${NavScreens.Homescreen.name}/${searchvalue.value.lowercase()}")

            }
                keyboardconttroller?.hide()


            }), textStyle = TextStyle(fontSize = 20.sp)
        )

    }
}