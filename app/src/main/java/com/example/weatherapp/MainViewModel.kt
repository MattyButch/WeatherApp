package com.example.weatherapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var message by mutableStateOf("Initial hello")
        private set

    fun updateMessage(newMessage: String) {
        message = newMessage
    }
}