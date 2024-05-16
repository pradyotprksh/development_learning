package core.utils

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
        const val ISSUER = ConstValues.BASE_URL
        const val DOMAIN = ConstValues.BASE_URL
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
            const val GENERATE_OTP = "/generate-otp"
            const val VALIDATE_OTP = "/validate-otp"
        }
    }

    object Keys {
        const val USER_ID = "userId"
        const val JWT_SECRET = "JWT_SECRET"
        const val CONTENT_TYPE = "Content-Type"
        const val AUTHORIZATION = "Authorization"
        const val REQUEST_IDENTIFIER = "request-identifier"
    }

    object ConstValues {
        const val USERNAME_LENGTH = 5
        const val NAME_LENGTH = 1
        const val PASSWORD_LENGTH = 8
        const val TOKEN_EXPIRES_IN = 365L * 1000L * 60L * 60L * 24L
        const val APPLICATION_JSON = "application/json"
        const val BEARER = "Bearer"
        const val BASE_URL = "http://${Server.HOST}:${Server.PORT}"
    }

    object ErrorCode {
        const val DEFAULT_ERROR_CODE = "XFS000"
        const val UNAUTHORIZED_ERROR_CODE = "XFS001"
        const val USER_DETAILS_NOT_FOUND_CODE = "XFS002"
        const val USER_AUTH_DETAILS_ERROR_CODE = "XFS003"
        const val DB_WRITE_ERROR_CODE = "XFS004"
        const val USERNAME_ALREADY_PRESENT_ERROR_CODE = "XFS005"
        const val EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE = "XFS006"
        const val EMAIL_ALREADY_PRESENT_ERROR_CODE = "XFS007"
        const val PHONE_NUMBER_ALREADY_PRESENT_ERROR_CODE = "XFS008"
        const val USERNAME_VALIDITY_ERROR_CODE = "XFS009"
        const val PASSWORD_VALIDITY_ERROR_CODE = "XFS010"
        const val EMAIL_VALIDITY_ERROR_CODE = "XFS011"
        const val PHONE_NUMBER_VALIDITY_ERROR_CODE = "XFS012"
        const val PROFILE_PICTURE_VALIDITY_ERROR_CODE = "XFS013"
        const val USERNAME_OR_EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE = "XFS014"
        const val NAME_VALIDITY_ERROR_CODE = "XFS015"
    }
}