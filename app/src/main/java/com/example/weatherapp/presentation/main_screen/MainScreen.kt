package com.example.weatherapp.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.presentation.navigation.MainNavigation

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navHostController: NavHostController = rememberNavController()
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            val currentScreen = navHostController.currentBackStackEntryAsState().value?.destination?.route
            AnimatedVisibility(visible = bottomNavItems.map { it.route }.contains(currentScreen)) {
                CustomBottomBar(navController = navHostController)
            }
        }) {
        MainNavigation(navHostController)
    }
}