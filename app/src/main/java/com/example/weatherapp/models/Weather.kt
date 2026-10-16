package com.example.weatherapp.models

import androidx.annotation.DrawableRes

data class Weather(
    val day: String,
    val name: String,
    val temp: Int,
    val resourceId: Int
)