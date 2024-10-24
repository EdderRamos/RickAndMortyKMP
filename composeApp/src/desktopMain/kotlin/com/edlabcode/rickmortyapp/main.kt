package com.edlabcode.rickmortyapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.edlabcode.rickmortyapp.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Rick and Morty App"
    ) {
        initKoin()
        App()
    }
}