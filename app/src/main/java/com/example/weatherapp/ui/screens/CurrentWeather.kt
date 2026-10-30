package com.example.weatherapp.ui.screens

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Composable
fun CurrentWeather(viewModel: MainViewModel) {
    Text(viewModel.message)

    Button(
        onClick = {
            viewModel.updateMessage("Changed from Current Weather")
        }
    ) {
        Text("Change Message")
    }
    Column() {
        Button(
            onClick = {

            }
        ) {

        }
    }

    val weather = Weather(
        day = "Today",
        name = "Sunny",
        temp = 16,
        resourceId = R.drawable.mcgsunsprite1
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(40.dp)
        )
        Text(
            text = weather.day,
            fontFamily = DisplayFont,
            fontSize = 32.sp,
//            ------------CODE TO MAKE IT GLOW----------------------------
//            Note: I removed the glowing because my wife said it looked a bit much. I'm leaving it in because I might revisit it.
            style = TextStyle(
                shadow = Shadow(
                    color = CRTTextGreen,
                    offset = Offset(0f, 0f),
                    blurRadius = 7f
                )
            )
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )


        Image(
            painter = painterResource(id = weather.resourceId),
            contentDescription = weather.name,
            modifier = Modifier.size(175.dp)
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(
            text = "${weather.temp}°",
            fontFamily = DisplayFont,
            fontSize = 32.sp,
//            -------------------CODE TO MAKE IT GLOW-------------------------
            style = TextStyle(
                shadow = Shadow(
                    color = CRTTextGreen,
                    offset = Offset(0f, 0f),
                    blurRadius = 7f
                )
            )
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = weather.name,
            fontFamily = BodyFont,
            fontSize = 28.sp,
//            -------------------CODE TO MAKE IT GLOW-------------------------
            style = TextStyle(
                shadow = Shadow(
                    color = CRTTextGreen,
                    offset = Offset(0f, 0f),
                    blurRadius = 7f
                )
            )
        )
    }
}