package com.example.weatherapp.presentation.main_screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.presentation.navigation.Screens

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String){
    object Home: BottomNavItem("Home", Icons.Outlined.Home, Screens.WeatherScreen.route)
    object Search: BottomNavItem("Search", Icons.Outlined.Search, Screens.SearchScreen.route)
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Search
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomBottomBar(
    navController: NavController){

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 20.dp,
        backgroundColor = androidx.compose.material.MaterialTheme.colors.background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.route
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = item.icon,
                        tint = if(isSelected) Color.Blue else Color.LightGray,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Gray,
                selected = isSelected,
                alwaysShowLabel = false,
                onClick = {

                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            //used pop up to avoid stack in bottom navigation
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}