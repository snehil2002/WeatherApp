package com.example.weatherapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.data.WeatherItem
import okhttp3.Route

@Composable fun topbarw(title:String,ismainscreen:Boolean,elevation:Dp,navcontroller: NavController,
searchbuttonclick :()->Unit={},backbuttonclick:()->Unit={},dropdownclick:()->Unit={},
favbuttonclick:()->Unit={},showfavbutton:MutableState<Boolean>?=null){

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(55.dp)
        .padding(7.dp),
    elevation=elevation) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
            if (ismainscreen == false) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button",
                modifier = Modifier
                    .size(35.dp)
                    .padding(5.dp)
                    .clickable { backbuttonclick() })
            }
            else{
                Box(modifier = Modifier
                    .size(35.dp)
                    .padding(5.dp)){}
            }
            if(ismainscreen==true && showfavbutton!!.value==true){
                Icon(imageVector = Icons.Default.Favorite, tint = androidx.compose.ui.graphics.Color.Red, contentDescription ="Favourite button",
                modifier = Modifier
                    .size(35.dp)
                    .padding(5.dp).clickable { favbuttonclick() })
            }
            else{
                Box(modifier = Modifier
                    .size(35.dp)
                    .padding(5.dp)){}

            }


            Text(text = title,
                fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold)



            if(ismainscreen==true){
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Back button",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(5.dp)
                        .clickable { searchbuttonclick() })

                Icon(imageVector = Icons.Default.MoreVert,
                    contentDescription = "Back button",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(5.dp)
                        .clickable { dropdownclick() })

            }
            else{
                Box(modifier = Modifier
                    .size(35.dp)
                    .padding(5.dp)){}
                Box(modifier = Modifier
                    .size(35.dp)
                    .padding(5.dp)){}
            }

        }

    }

}

@Composable fun humiditypresswind(humidity:String,press:String,
wind:String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp),
    horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.drawable.humidity) , contentDescription ="", modifier = Modifier.size(25.dp) )
            Text(text = humidity)

        }
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.drawable.pressure) , contentDescription ="", modifier = Modifier.size(25.dp) )
            Text(text = press)

        }
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(id = R.drawable.wind) , contentDescription ="", modifier = Modifier.size(25.dp) )
            Text(text = wind)

        }


    }

}

@Composable fun sunriseset(sunrise:Int,sunset:Int){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.sunrise), contentDescription ="",
            modifier = Modifier.size(35.dp))
            Text(timestamptotime(sunrise))

        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(timestamptotime(sunset))
            Image(painter = painterResource(id = R.drawable.sunset), contentDescription ="",
                modifier = Modifier.size(35.dp))


        }


    }


}

@Composable fun weekrow(listitem:WeatherItem){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .padding(horizontal = 5.dp, vertical = 3.dp)
        .clip(
            RoundedCornerShape(
                topStart = 40.dp, bottomStart = 40.dp,
                bottomEnd = 40.dp
            )
        )
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(5.dp), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = timestamptodate(listitem.dt).split(",")[0],
            fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Image(painter = rememberImagePainter(data = "https://openweathermap.org/img/wn/${listitem.weather[0].icon}.png"), contentDescription = "",
            modifier = Modifier.size(100.dp))
            Surface(shape = RoundedCornerShape(20.dp), color = Color(0xFFECBD11)) {
                Text(text = listitem.weather[0].description, modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                fontWeight = FontWeight.SemiBold)

            }
            Row() {
                Text(text = "${listitem.temp.max.toInt()}°", modifier = Modifier.padding(2.dp), color = Color.Blue,
                fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "${listitem.temp.min.toInt()}°",modifier = Modifier.padding(2.dp),color = Color.LightGray,
                    fontWeight = FontWeight.Bold, fontSize = 20.sp)

            }
        }

    }
}

@Composable fun dropdown(showdropdown:MutableState<Boolean>,navcontoller:NavController,
items:List<String>,itemicons:List<ImageVector>,routelist:List<String>){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(right = 10.dp)
    ) {

    DropdownMenu(expanded = showdropdown.value, onDismissRequest = { showdropdown.value=false},
    ) {
        items.forEachIndexed{index, text->
            DropdownMenuItem(onClick = {
                                       navcontoller.navigate(routelist[index])
                showdropdown.value=false

            }, modifier = Modifier) {
                Icon(imageVector = itemicons[index], contentDescription ="" )
                Text(text = text, fontSize = 15.sp, modifier = Modifier.padding(4.dp))

            }


        }

    }}


}