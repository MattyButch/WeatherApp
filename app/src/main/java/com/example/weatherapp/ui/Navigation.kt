package com.example.weatherapp.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.WeatherForecast
import com.example.weatherapp.ui.screens.CurrentWeather
import com.example.weatherapp.ui.theme.MonitorGrey
import com.example.weatherapp.ui.theme.CRTTextGreen
import com.example.weatherapp.ui.theme.IBMRed
import com.example.weatherapp.ui.theme.NavDisplayFont
import com.example.weatherapp.ui.theme.CRTAmber
import com.example.weatherapp.ui.theme.CRTDarkGrey
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.LaunchedEffect
//Imports to make it glow
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.ui.screens.CurrentAstro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val navController = rememberNavController()
    var selectedIndex by remember {mutableIntStateOf(0)}

    //Intilizite view model in one place (shared view model), and pass it into different screens
    val viewModel: MainViewModel = viewModel()
    // Begin: Ask permission to get device location.
        // Note: If permission is previously granted, the dialog won't appear and refreshLocation() will get called.
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        if (results.values.any { it }) {
            viewModel.refreshLocation() // call a function to get the current location for device
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        }
        // End: Ask permission to get device location.



    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MonitorGrey,
                    titleContentColor = CRTTextGreen,
                ),
                title = {
                    if (selectedIndex == 0){
                        Text(
                            text = viewModel.weather?.let {
                                "${it.location.name}, ${it.location.region}"
                            } ?: "Loading...",
                            fontFamily = NavDisplayFont,
                            color = IBMRed
                        )
                    }
                    else if(selectedIndex == 1){
                        Text(
                            text = viewModel.weather?.let {
                                "${it.location.name}, ${it.location.region}"
                            } ?: "Loading...",
                            fontFamily = NavDisplayFont,
                            color = IBMRed)
                    }
                    else if(selectedIndex == 2){
                        Text(
                            text = viewModel.weather?.let {
                                "${it.location.name}, ${it.location.region}"
                            } ?: "Loading...",
                            fontFamily = NavDisplayFont,
                            color = IBMRed)
                    }
                }
            )
        },
                bottomBar = {
            NavigationBar(
                containerColor = MonitorGrey,
                windowInsets = NavigationBarDefaults.windowInsets)
            {
                //Current Weather
                NavigationBarItem(
                     icon = { Icon(imageVector = Icons.Default.Thermostat, contentDescription = "Current Weather") },
                     label = { Text("Current Weather") },
                     selected = selectedIndex == 0,
                     colors = NavigationBarItemDefaults.colors( indicatorColor = CRTDarkGrey),
                     onClick = {
                         selectedIndex = 0
                         navController.navigate(route="CurrentWeather"){
                             popUpTo(navController.graph.findStartDestination().id){
                                 saveState = true
                             }
                             launchSingleTop = true
                             restoreState = true
                         }
                     }
                 )
                //Forecast
                NavigationBarItem(
                    icon = {Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "Forecast")},
                    label = {Text("Forecast")},
                    selected = selectedIndex == 1,
                    colors = NavigationBarItemDefaults.colors( indicatorColor = CRTDarkGrey),
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(route="WeatherForecast"){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

                //Astro
                NavigationBarItem(
                    icon = { Icon(imageVector = Icons.Default.DarkMode,contentDescription = "Astro")},
                    label = {Text("Astro") },
                    selected = selectedIndex == 2,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = CRTDarkGrey
                    ),
                    onClick = {
                        selectedIndex = 2

                        navController.navigate(route = "Astro") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

            }
        }
    ) //End Scaffold
    { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "CurrentWeather",
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = "CurrentWeather"){
                CurrentWeather(viewModel)
            }
            composable(route = "WeatherForecast"){
                WeatherForecast(viewModel)
            }
            composable(route = "Astro") {
                CurrentAstro(viewModel)
            }
        }
    }
}

