package com.pradyotprakash.unitconverter.utils

object Constants {
    object Server {
        const val PORT = 8080
        const val HOST = "192.168.191.17"
    }

    object Paths {
        object Converter {
            const val CONVERT = "/convert"
            const val LENGTH = "/length"
            const val WEIGHT = "/weight"
            const val TEMPERATURE = "/temperature"
        }
    }

    object ConstValues {
        const val TIMEOUT_VALUE = 15000L
        const val BASE_URL = "http://${Server.HOST}:${Server.PORT}"
    }
}