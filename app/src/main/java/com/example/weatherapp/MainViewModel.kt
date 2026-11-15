package com.example.weatherapp

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.RetrofitInstance
import com.example.weatherapp.data.dao.Condition
import com.example.weatherapp.data.dao.Current
import com.example.weatherapp.data.dao.Location as WeatherLocation
import com.example.weatherapp.data.dao.WeatherModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.launch
import android.location.Location
import com.example.weatherapp.data.dao.Forecast


class MainViewModel(app: Application) : AndroidViewModel(app) {

    var weather by mutableStateOf<WeatherModel?>(null)
        private set
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)
    var location by mutableStateOf<Location?>(null)


    @SuppressLint("MissingPermission") // silence warning
    fun refreshLocation() {
        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { result ->
                location = result
                refreshWeatherLocation()
                val latlng = "lat: ${location?.latitude}, lng: ${location?.longitude}"
                Log.i("MainViewModel", "Phone is location at $latlng.")
            }
    }

    fun refreshWeatherLocation() {

        val currentLocation = location ?: return

        getWeather(
            "${currentLocation.latitude},${currentLocation.longitude}"
        )
    }
    fun getWeather(query: String) {

        viewModelScope.launch {



            try {

                weather = RetrofitInstance.api.getWeather(
                    key = "f9c0c947c5c04af0a50172447262010",
                    query = query

                )


            } catch (e: Exception) {

                weather = WeatherModel(
                    location = WeatherLocation(
                        name = e.javaClass.simpleName,
                        region = "ERROR",
                        country = "ERROR",
                        localtime = "ERROR"
                    ),
                    current = Current(
                        temp_c = -1f,
                        condition = Condition(
                            text = e.message ?: "Unknown Error",
                            icon = "",
                            code = 1000
                        ),
                        humidity = 0,
                        wind_kph = 0f,
                        uv = 0f,
                        precip_mm = 0f,
                        is_day = 1,
                    ),
                    forecast = Forecast(
                        forecastday = emptyList()
                    )
                )



            }
        }
    }
}