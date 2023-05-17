package com.example.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.presentation.weather_screen.WeatherScreen
import com.example.weatherapp.presentation.weather_screen.WeatherViewModel
import com.example.weatherapp.presentation.main_screen.BottomNavItem
import com.example.weatherapp.presentation.main_screen.MainScreen
import com.example.weatherapp.presentation.search_screen.SearchScreen


@Composable
fun WeatherNavigation(
    navController: NavHostController,
    //isFirstTime: Boolean wenn Onboarding Screen gebraucht wird
    ){
    
    NavHost(navController = navController, startDestination = Screens.WeatherScreen.route){
        composable(route = Screens.WeatherScreen.route){
            MainScreen()
        }
    }
}

@Composable
fun MainNavigation(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel = hiltViewModel()
){
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route){
        composable(route = BottomNavItem.Home.route){
            WeatherScreen(weatherViewModel)
        }
        composable(route = BottomNavItem.Search.route){
            SearchScreen()
        }
    }
}



