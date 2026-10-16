package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.weatherapp.models.Weather
import com.example.weatherapp.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.BodyFont
import com.example.weatherapp.ui.theme.CRTGreen
import com.example.weatherapp.ui.theme.CRTTextGreen
import com.example.weatherapp.ui.theme.DisplayFont


@Composable
fun WeatherForecast(innerPadding: PaddingValues) {
    val listWeather = listOf(
        Weather(
            day = "Tuesday",
            name = "Sunny",
            temp = 16,
            resourceId = R.drawable.mcgsunsprite1
        ),
        Weather(
            day = "Wednesday",
            name = "Rainy",
            temp = 11,
            resourceId = R.drawable.mcgrainysprite
        ),
        Weather(
            day = "Thursday",
            name = "Cloudy",
            temp = 11,
            resourceId = R.drawable.mcgcloudsprite2
        ),
        Weather(
            day = "Friday",
            name = "Snowy",
            temp = -10,
            resourceId = R.drawable.mcgsnowysprite
        ),
        Weather(
            day = "Saturday",
            name = "Cloudy",
            temp = 5,
            resourceId = R.drawable.mcgcloudsprite2
        ),
        Weather(
            day = "Sunday",
            name = "Sunny",
            temp = 5,
            resourceId = R.drawable.mcgsunsprite1
        ),
        Weather(
            day = "Monday",
            name = "Thunderstorm",
            temp = 5,
            resourceId = R.drawable.mcglightning
        ),

    )
    LazyColumn(
        modifier = Modifier.padding(innerPadding)
    ) {
        items(listWeather) { weather ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = weather.resourceId),
                    contentDescription = weather.name
                )
                Spacer(
                    modifier = Modifier.width(24.dp)
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = weather.day,
                        fontFamily = DisplayFont,
                        fontSize = 18.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = CRTTextGreen,
                                offset = Offset(0f, 0f),
                                blurRadius = 7f
                            )
                        )
                    )


                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = weather.name,
                        fontFamily = BodyFont,
                        fontSize = 16.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = CRTTextGreen,
                                offset = Offset(0f, 0f),
                                blurRadius = 7f
                            )
                        )
                    )
                }
                Text(
                    text = "${weather.temp}°",
                    fontFamily = DisplayFont,
                    fontSize = 16.sp,
                    style = TextStyle(
                        shadow = Shadow(
                            color = CRTTextGreen,
                            offset = Offset(0f, 0f),
                            blurRadius = 7f
                        )
                    )
                )
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = CRTTextGreen
            )
        }
    }
}