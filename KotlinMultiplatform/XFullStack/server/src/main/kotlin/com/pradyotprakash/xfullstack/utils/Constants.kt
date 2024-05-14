package com.pradyotprakash.xfullstack.utils

object Constants {
    object Server {
        const val PORT = 8080
        const val HOST = "192.168.1.37"
    }

    object Database {
        const val MONGODB_URI = "MONGODB_URI"
        const val DEFAULT_MONGODB_URI =
            "mongodb+srv://<usename>:<password>@cluster0.sq3aiau.mongodb.net/?retryWrites=true&w=majority"
        const val DATABASE_NAME = "xfullstack"

        object Collections {
            const val USERS = "users"
        }
    }

    object Jwt {
        const val ISSUER = "http://${Server.HOST}:${Server.PORT}"
        const val DOMAIN = "http://${Server.HOST}:${Server.PORT}"
        const val AUDIENCE = "users"
        const val REALM = "xfullstack ktor app"
    }

    object Paths {
        object Authentication {
            const val AUTH = "/auth"
            const val LOGIN = "/login"
            const val REGISTER = "/register"
            const val AUTHENTICATE = "/authenticate"
            const val USER_INFO = "/user-info"
        }

        object Utils {
            const val UTILS = "/utils"
            const val USERNAME_VALID = "/username-valid"
        }
    }

    object Keys {
        const val USER_ID = "userId"
    }

    object ConstValues {
        const val USERNAME_LENGTH = 5
    }
}