package com.pradyotprakash.personalblog.utils

object Constants {
    object Server {
        const val PORT = 8080
        const val HOST = "192.168.191.17"
    }

    object Paths {
        object PersonalBlog {

        }
    }

    object ConstValues {
        const val TIMEOUT_VALUE = 15000L
        const val BASE_URL = "http://${Server.HOST}:${Server.PORT}"
    }
}