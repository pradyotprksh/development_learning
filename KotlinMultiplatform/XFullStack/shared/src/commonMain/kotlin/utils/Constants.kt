package utils

object Constants {
    object Server {
        const val PORT = 8080
        const val HOST = "192.168.1.39"
    }

    object Database {
        const val MONGODB_URI = "MONGODB_URI"
        const val DEFAULT_MONGODB_URI =
            "mongodb+srv://<usename>:<password>@cluster0.sq3aiau.mongodb.net/?retryWrites=true&w=majority"
        const val DATABASE_NAME = "xfullstack"

        object Collections {
            const val USERS = "users"
            const val TWEET = "tweet"
            const val VIEWS = "views"
            const val FOLLOW = "follow"
            const val TAGS = "tags"
            const val BOOKMARK = "bookmark"
        }
    }

    object Jwt {
        const val ISSUER = ConstValues.BASE_URL
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
            const val SERVER_AVAILABLE = "/server-available"
            const val USERNAME_VALID = "/username-valid"
        }

        object Verification {
            const val VERIFICATION = "/verification"
            const val GENERATE_OTP = "/generate-otp"
            const val VALIDATE_OTP = "/validate-otp"
            const val USER_PRESENT = "/user-present"
        }

        object Websockets {
            const val WEBSOCKETS = "/websockets"
        }

        object Tweets {
            const val TWEET = "/tweet"
            const val TWEET_VOTE = "/poll"
            const val PAGINATE = "/paginate"
        }

        object Views {
            const val VIEWS = "/views"
        }

        object Secrets {
            const val SECRETS = "/secrets"
        }

        object Gemini {
            const val BETA_1_5_MODEL_GENERATE_CONTENT =
                "v1beta/models/gemini-1.5-flash:generateContent"
        }

        object Migration {
            const val MIGRATION = "/migration"
        }

        object Follow {
            const val FOLLOW = "/follow"
            const val UPDATE = "/update"
        }

        object Bookmarks {
            const val BOOKMARK = "/bookmark"
            const val UPDATE = "/update"
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
        const val PAGE = "page"
        const val LIMIT = "limit"
        const val TWEET_ID = "tweetId"
        const val OPTION_ID = "optionId"
        const val GEMINI_API_KEY = "gemini_api_key"
        const val GEMINI_API_KEY_1 = "GEMINI_API_KEY_1"
        const val GEMINI_API_KEY_2 = "GEMINI_API_KEY_2"
        const val KEY = "key"
        const val FOR_YOU_SCROLL_POSITION = "for_you_scroll_position"
        const val FOLLOWING_SCROLL_POSITION = "following_scroll_position"
        const val FOLLOWING_ID = "followingId"
        const val TWEET_REQUEST = "tweetRequest"
        const val FOLLOW_REQUEST = "followRequest"
        const val BOOKMARK_REQUEST = "bookmarkRequest"
        const val POLL_REQUEST = "pollRequest"
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
        const val GEMINI_BASE_URL =
            "https://generativelanguage.googleapis.com/"
        const val OTP_LENGTH = 6
        const val USERNAME_EMAIL_PHONE = "usernameEmailPhone"
        const val PARENT_TWEET_ID = "parentTweetId"
        const val IS_RETWEET = "isRetweet"
        const val IS_REPLY = "isReply"
        const val TWEET_ID = "tweetId"
        const val USER_ID = "userId"
        const val NO_NAV_VALUE = "no-nav-value"
        const val BIO_MAX_LENGTH = 50
        const val USERNAME_PREFIX = "@"
        const val TWEET_MAX_LENGTH = 250
        const val DEFAULT_PAGINATE_LIMIT = 100
        const val MAX_TWEET_CREATION_LIMIT = 25
        const val MAX_POLL_CHOICE_LENGTH = 25
        const val XFULLSTACK_HTTP_CLIENT = "x_full_stack_http_client"
        const val GEMINI_HTTP_CLIENT = "gemini_http_client"
        const val XFULLSTACK_NETWORK_CLIENT = "x_full_stack_network_client"
        const val GEMINI_NETWORK_CLIENT = "gemini_network_client"
        const val TIMEOUT_VALUE = 15000L
        const val ENABLE_GEMINI = false
        const val TAG_REGEX = "#[a-zA-Z0-9]+"
        const val TERMS = "terms"
        const val PRIVACY_POLICY = "privacy_policy"
        const val COOKIE_USE = "cookie_use"
        const val LOG_IN = "log_in"
        const val CREATED_BY = "created_by"
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
        const val TWEET_VALIDITY_ERROR_CODE = "EC_XFS020"
        const val BAD_REQUEST_ERROR_CODE = "EC_XFS021"
        const val INVALID_REQUEST_ASK = "EC_XFS022"
        const val SAME_FOLLOW_FOLLOWING_ID = "EC_XFS023"
    }

    object SuccessCode {
        const val USERNAME_PRESENT_SUCCESS_CODE = "SC_XFS001"
        const val EMAIL_PRESENT_SUCCESS_CODE = "SC_XFS002"
        const val PHONE_NUMBER_PRESENT_SUCCESS_CODE = "SC_XFS003"
        const val TWEETS_UPDATE_SUCCESS_CODE = "SC_XFS004"
        const val TWEETS_DELETED_SUCCESS_CODE = "SC_XFS004"
        const val DB_MIGRATION_SUCCESS_CODE = "SC_XFS005"
        const val FOLLOW_UPDATE_SUCCESS = "SC_XFS006"
        const val WEBSOCKET_CONNECTION_SUCCESS = "SC_XFS007"
    }

    object DbKeys {
        const val ID = "_id"
        const val EMAIL_ADDRESS = "email_address"
        const val PHONE_NUMBER = "phone_number"
        const val PROFILE_PICTURE = "profile_picture"
        const val DATE_OF_BIRTH = "date_of_birth"
        const val USER_ID = "user_id"
        const val BOOKMARKED_AT = "bookmarked_at"
        const val CREATED_BY = "created_by"
        const val TWEETED_ON = "tweeted_on"
        const val IS_A_POLL = "is_a_poll"
        const val POLL_CHOICES = "poll_choices"
        const val POLL_HOUR = "poll_hour"
        const val POLL_MINUTE = "poll_minute"
        const val POLL_SECONDS = "poll_seconds"
        const val POLLING_END_TIME = "polling_end_time"
        const val IS_POLLING_ALLOWED = "is_polling_allowed"
        const val IS_A_LOCATION = "is_a_location"
        const val POLL_LENGTH = "poll_length"
        const val SCHEDULED_ON_TWEET = "scheduled_on_tweet"
        const val IS_SCHEDULED_TWEET = "is_scheduled_tweet"
        const val PARENT_TWEET_DETAILS_NOT_FOUND = "parent_tweet_details_not_found"
        const val IS_A_COMMENT_TWEET = "is_a_comment_tweet"
        const val PARENT_TWEET_ID = "parent_tweet_id"
        const val PARENT_TWEET_DETAILS = "parent_tweet_details"
        const val IS_A_QUOTE_TWEET = "is_a_quote_tweet"
        const val COMMENT_COUNT = "comment_count"
        const val LIKE_COUNT = "like_count"
        const val REPOST_COUNT = "repost_count"
        const val QUOTES_COUNT = "quotes_count"
        const val BOOKMARK_COUNT = "bookmark_count"
        const val IS_A_REPOST_TWEET = "is_a_repost_tweet"
        const val IS_A_LIKED_TWEET = "is_a_liked_tweet"
        const val IS_LIKED_BY_CURRENT_USER = "is_liked_by_current_user"
        const val IS_BOOKMARKED_BY_CURRENT_USER = "is_bookmarked_by_current_user"
        const val VOTE_COUNT = "vote_count"
        const val VOTED_BY = "voted_by"
        const val VOTER_DETAILS = "voter_details"
        const val VOTED_ON = "voted_on"
        const val TWEETED_ON_TIMESTAMP = "tweeted_on_timestamp"
        const val TWEET_ID = "tweet_id"
        const val VIEWED_BY = "viewed_by"
        const val VIEWED_ON = "viewed_on"
        const val VIEWED_ID = "viewed_id"
        const val FOLLOWER_ID = "follower_id"
        const val FOLLOWING_ID = "following_id"
        const val FOLLOWED_AT = "follower_at"
        const val NATURE = "nature"
        const val MEDIA = "media"
        const val GIF = "gif"
        const val REQUEST_ID = "request_id"
        const val REQUEST_TYPE = "request_type"
        const val IS_UPDATED_ONLINE = "is_updated_online"
        const val IS_FOLLOWED_BY_CURRENT_USER = "is_followed_by_current_user"
        const val IS_FOLLOWING_CURRENT_USER = "is_following_current_user"
        const val IS_SAME_USER = "is_same_user"
        const val COUNT = "count"
    }

    object GeminiPrompt {
        const val TWEET_EMOTION =
            "can you tell the tag for the text \"%s\", like what's the tone or emotion give response in words, separated by comma."
        const val USER_NATURE =
            "user created posts with emotions \"%s\". can you tell what kind of nature this person has. give response in words, separated by comma."
    }
}