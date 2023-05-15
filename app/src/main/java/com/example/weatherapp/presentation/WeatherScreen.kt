package com.example.weatherapp.presentation

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
){
    val lifecycleOwner = LocalLifecycleOwner.current
    val state = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver{ _, event ->
            when (event){
                Lifecycle.Event.ON_START -> {
                    state.launchMultiplePermissionRequest()
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        
        onDispose { 
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })
    
    PermissionsRequired(
        multiplePermissionsState = state,
        permissionsNotGrantedContent = {
            CustomAlertDialog()
        },
        permissionsNotAvailableContent = {
            CustomAlertDialog()
        }
    ) {
        LaunchedEffect(key1 = Unit){
            viewModel.loadWeatherInfo()
        }
    }
    SetBackgroundImage(viewModel.state)
    viewModel.state.weatherInfo?.currentWeatherData?.let { data ->
        print(data.city)
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        )
        {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 80.dp)
                    .height(350.dp)
                    .shadow(15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${data.time.dayOfWeek.toString().uppercase()}\n" +
                                    data.weatherType.weatherDesc.uppercase(),
                            color = data.weatherType.textcolor
                        )
                        Text(
                            text = data.city.uppercase(),
                            color = data.weatherType.textcolor
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 20.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${data.temperature.toInt()}°",
                            color = data.weatherType.textcolor,
                            fontSize = 100.sp
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(text = "Humidity: ${data.humidity}", color = data.weatherType.textcolor)
                        Text(text = "Wind Speed: ${data.windSpeed}", color = data.weatherType.textcolor)
                        Text(text = "Wind direction: ${Util.getWindDirection(data.windDirection)}", color = data.weatherType.textcolor)
                    }
                }
            }
        }
    }
}

@Composable
fun SetBackgroundImage(
    state: WeatherState
) {
    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false
    systemUiController.isNavigationBarVisible = false
    systemUiController.isSystemBarsVisible = false
    state.weatherInfo?.currentWeatherData?.let{data ->
        Image(
            painter = painterResource(id = data.weatherType.backgroundRes),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun CustomAlertDialog() {
    Text(text = "CustomAlertDialog")
}
