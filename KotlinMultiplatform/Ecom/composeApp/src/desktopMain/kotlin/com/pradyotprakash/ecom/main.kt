package com.pradyotprakash.ecom

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Ecom",
    ) {
        App()
    }
}