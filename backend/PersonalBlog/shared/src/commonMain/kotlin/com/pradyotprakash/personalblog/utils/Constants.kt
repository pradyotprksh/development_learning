package com.pradyotprakash.personalblog.utils

object Constants {
    object Server {
        const val PORT = 8080
        const val HOST = "192.168.191.17"
    }

    object Paths {
        object PersonalBlog {
            const val BLOG = "/blog"
            const val ADD = "/add"
            const val UPDATE = "/update"
        }
    }

    object ConstValues {
        const val TIMEOUT_VALUE = 15000L
        const val BASE_URL = "http://${Server.HOST}:${Server.PORT}"
    }

    object Keys {
        const val SUPABASE_API_KEY = "SUPABASE_API_KEY"
        const val SUPABASE_URL = "SUPABASE_URL"
    }
}