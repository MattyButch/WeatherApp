package com.example.weatherapp.ui.screens

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.models.Weather
import com.example.weatherapp.ui.theme.BodyFont
import com.example.weatherapp.ui.theme.DisplayFont
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.ui.theme.CRTTextGreen
import com.example.weatherapp.weathericons.WeatherIconMapper
import com.example.weatherapp.weathericons.toDrawable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
//Make it sideways scrollable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

//------------CODE TO MAKE IT GLOW----------------------------
private val GlowText = TextStyle(
    shadow = Shadow(
        color = CRTTextGreen,
        offset = Offset(0f, 0f),
        blurRadius = 7f
    )
)


@Composable

fun CurrentWeather(viewModel: MainViewModel) {

    val weather = viewModel.weather



weather?.let{ currentWeather ->

        val dayOfWeek =
            LocalDateTime
                .parse(
                    currentWeather.location.localtime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                )
                .dayOfWeek
                .toString()
                .lowercase()
                .replaceFirstChar { it.uppercase() }
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(
                modifier = Modifier.height(40.dp)
            )
            Text(
                text = dayOfWeek,
                fontFamily = DisplayFont,
                fontSize = 28.sp,
                style = GlowText

            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )


            val weatherIcon = WeatherIconMapper.getIcon(
                weather.current.condition.code,
                weather.current.is_day
            )

            Image(
                painter = painterResource(
                    weatherIcon.toDrawable()
                ),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
//            Image(
//                painter = painterResource(id = weather.resourceId),
//                contentDescription = weather.name,
//                modifier = Modifier.size(175.dp)
//            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Text(
                text = "${currentWeather.current.temp_c.toInt()}°C",
                fontFamily = DisplayFont,
                fontSize = 28.sp,
////            -------------------CODE TO MAKE IT GLOW-------------------------
                style = GlowText
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = currentWeather.current.condition.text,
                fontFamily = BodyFont,
                fontSize = 20.sp,
////            -------------------CODE TO MAKE IT GLOW-------------------------
                style = GlowText
            )
            Spacer(
                modifier = Modifier.height(32.dp)
            )

            HorizontalDivider(
                color = CRTTextGreen
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Humidity",
                        fontFamily = BodyFont,
                        fontSize = 18.sp,
                        style = GlowText
                    )
                    Text(
                        text = "${currentWeather.current.humidity}%",
                        fontFamily = BodyFont,
                        fontSize = 18.sp,
                        style = GlowText
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Wind",
                        fontFamily = BodyFont,
                        fontSize = 18.sp,
                        style = GlowText
                    )
                    Text(
                        text = "${currentWeather.current.wind_kph} km/h",
                        fontFamily = BodyFont,
                        fontSize = 18.sp,
                        style = GlowText
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "UV Index",
                        fontFamily = BodyFont,
                        fontSize = 18.sp,
                        style = GlowText
                    )
                    Text(
                        text = "${currentWeather.current.uv}",
                        fontFamily = BodyFont,
                        fontSize = 18.sp,
                        style = GlowText
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Rain",
                        fontFamily = BodyFont,
                        fontSize = 18.sp,
                        style = GlowText
                    )
                    Text(
                        text = "${currentWeather.current.precip_mm} mm",
                        fontFamily = BodyFont,
                        fontSize = 18.sp,
                        style = GlowText
                    )
                }


            }//End of second column
        } //End of first column
        }
    }

