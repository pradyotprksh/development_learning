package utils

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
        }

        object Verification {
            const val VERIFICATION = "/verification"
            const val GENERATE_OTP = "/generate-otp"
            const val VALIDATE_OTP = "/validate-otp"
            const val USER_PRESENT = "/user-present"
        }
    }

    object Keys {
        const val USER_ID = "userId"
        const val JWT_SECRET = "JWT_SECRET"
        const val CONTENT_TYPE = "Content-Type"
        const val AUTHORIZATION = "Authorization"
        const val USER_ID_HEADER = "User-Id"
        const val REQUEST_IDENTIFIER = "request-identifier"
        const val REQUEST_TIMESTAMP = "request-time"
        const val VALUE = "value"
    }

    object ConstValues {
        const val USERNAME_MIN_LENGTH = 5
        const val NAME_MIN_LENGTH = 1
        const val NAME_MAX_LENGTH = 50
        const val PASSWORD_MIN_LENGTH = 8
        const val TOKEN_EXPIRES_IN = 365L * 1000L * 60L * 60L * 24L
        const val API_RESPONSE_DELAY = 1500L
        const val APPLICATION_JSON = "application/json"
        const val BEARER = "Bearer"
        const val BASE_URL = "http://${Server.HOST}:${Server.PORT}"
        const val OTP_LENGTH = 6
        const val USERNAME_EMAIL_PHONE = "usernameEmailPhone"
        const val NO_USERNAME = "no-username"
        const val BIO_MAX_LENGTH = 50
        const val USERNAME_PREFIX = "@"
    }

    object ErrorCode {
        const val DEFAULT_ERROR_CODE = "EC_XFS000"
        const val UNAUTHORIZED_ERROR_CODE = "EC_XFS001"
        const val USER_DETAILS_NOT_FOUND_CODE = "EC_XFS002"
        const val USER_AUTH_DETAILS_ERROR_CODE = "EC_XFS003"
        const val DB_WRITE_ERROR_CODE = "EC_XFS004"
        const val USERNAME_ALREADY_PRESENT_ERROR_CODE = "EC_XFS005"
        const val EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE = "EC_XFS006"
        const val EMAIL_ALREADY_PRESENT_ERROR_CODE = "EC_XFS007"
        const val PHONE_NUMBER_ALREADY_PRESENT_ERROR_CODE = "EC_XFS008"
        const val USERNAME_VALIDITY_ERROR_CODE = "EC_XFS009"
        const val PASSWORD_VALIDITY_ERROR_CODE = "EC_XFS010"
        const val EMAIL_VALIDITY_ERROR_CODE = "EC_XFS011"
        const val PHONE_NUMBER_VALIDITY_ERROR_CODE = "EC_XFS012"
        const val PROFILE_PICTURE_VALIDITY_ERROR_CODE = "EC_XFS013"
        const val USERNAME_OR_EMAIL_OR_PHONE_NUMBER_REQUIRED_ERROR_CODE = "EC_XFS014"
        const val NAME_VALIDITY_ERROR_CODE = "EC_XFS015"
        const val OTP_GENERATION_ERROR_CODE = "EC_XFS016"
        const val OTP_VALIDATION_ERROR_CODE = "EC_XFS017"
        const val INTERNAL_SERVER_ERROR_CODE = "EC_XFS018"
        const val BIO_VALIDITY_ERROR_CODE = "EC_XFS019"
    }

    object SuccessCode {
        const val USERNAME_PRESENT = "SC_XFS001"
        const val EMAIL_PRESENT = "SC_XFS002"
        const val PHONE_NUMBER_PRESENT = "SC_XFS003"
    }

    object DbKeys {
        const val ID = "_id"
        const val EMAIL_ADDRESS = "email_address"
        const val PHONE_NUMBER = "phone_number"
        const val PROFILE_PICTURE = "profile_picture"
        const val DATE_OF_BIRTH = "date_of_birth"
        const val USER_ID = "userId"
    }
}