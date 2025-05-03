package com.pradyotprakash.personalblog.utils

object Constants {
    object Server {
        const val PORT = 8080
        const val HOST = "192.168.0.116"
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
        const val DATABASE_NAME = "PersonalBlog"
        const val ID = "id"
        const val CREATED_AT = "created_at"
        const val PUBLICATION_AT = "publication_at"
        const val TITLE = "title"
        const val CONTENT = "content"
    }
}