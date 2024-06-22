package utils

import core.exception.InvalidParameter
import core.exception.InvalidTweet
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import utils.Constants.ConstValues.BIO_MAX_LENGTH
import utils.Constants.ConstValues.NAME_MAX_LENGTH
import utils.Constants.ConstValues.NAME_MIN_LENGTH
import utils.Constants.ConstValues.PASSWORD_MIN_LENGTH
import utils.Constants.ConstValues.TAG_REGEX
import utils.Constants.ConstValues.TWEET_MAX_LENGTH
import utils.Constants.ConstValues.USERNAME_MIN_LENGTH
import utils.Constants.ErrorCode.BIO_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.EMAIL_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.NAME_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.PASSWORD_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.PHONE_NUMBER_VALIDITY_ERROR_CODE
import utils.Constants.ErrorCode.USERNAME_VALIDITY_ERROR_CODE
import utils.timeago.TimeAgo
import kotlin.math.absoluteValue

object UtilsMethod {
    object Validation {
        fun isPollTweetValid(
            tweet: String?,
            pollChoices: List<String>,
            pollHour: Long?,
        ): Boolean {
            isTweetValid(tweet)

            if (pollChoices.none { it.isNotBlank() }) {
                throw InvalidTweet(
                    message = Localization.INVALID_POLL_CHOICES
                )
            }

            if (pollHour == null) {
                throw InvalidTweet(
                    message = Localization.INVALID_POLL_TIMING
                )
            }

            return true
        }

        fun isTweetValid(tweet: String?): Boolean {
            tweet?.let {
                if (it.length !in 1..TWEET_MAX_LENGTH) {
                    throw InvalidTweet(
                        message = Localization.INVALID_TWEET_LENGTH
                    )
                }
            } ?: throw InvalidTweet(
                message = Localization.INVALID_TWEET_LENGTH
            )

            return true
        }

        fun isValidBio(bio: String): Boolean {
            if (bio.length !in 0..BIO_MAX_LENGTH) {
                throw InvalidParameter(
                    message = Localization.BIO_MAX_LENGTH_ERROR, errorCode = BIO_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        private fun maxNameLengthValid(username: String) =
            username.length in NAME_MIN_LENGTH..NAME_MAX_LENGTH

        fun isValidName(name: String): Boolean {
            if (!maxNameLengthValid(name)) {
                throw InvalidParameter(
                    message = Localization.NAME_LENGTH_ERROR, errorCode = NAME_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        private fun maxUsernameLengthValid(username: String) =
            username.length >= USERNAME_MIN_LENGTH

        private fun validUsername(username: String) = username.matches(Regex("[^\\n]{5,}"))

        fun isValidUserName(username: String): Boolean {
            if (!maxUsernameLengthValid(username)) {
                throw InvalidParameter(
                    message = Localization.USERNAME_LENGTH_ERROR,
                    errorCode = USERNAME_VALIDITY_ERROR_CODE
                )
            }

            if (!validUsername(username)) {
                throw InvalidParameter(
                    message = Localization.INVALID_USERNAME,
                    errorCode = USERNAME_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        fun minPasswordLengthValid(password: String) = password.length >= PASSWORD_MIN_LENGTH

        fun passwordContainsAtLeastOneUpperCase(password: String) =
            password.contains(Regex("[A-Z]"))

        fun passwordContainsAtLeastOneLowerCase(password: String) =
            password.contains(Regex("[a-z]"))

        fun passwordContainsAtLeastOneDigit(password: String) = password.contains(Regex("[0-9]"))

        fun passwordContainsAtLeastOneSpecialCharacters(password: String) =
            password.contains(Regex("[^A-Za-z0-9]"))

        fun isValidPassword(password: String): Boolean {
            if (!minPasswordLengthValid(password)) {
                throw InvalidParameter(
                    message = Localization.PASSWORD_LENGTH_ERROR,
                    errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            if (!passwordContainsAtLeastOneUpperCase(password)) {
                throw InvalidParameter(
                    message = Localization.NO_UPPERCASE_ERROR,
                    errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            if (!passwordContainsAtLeastOneLowerCase(password)) {
                throw InvalidParameter(
                    message = Localization.NO_LOWERCASE_ERROR,
                    errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            if (!passwordContainsAtLeastOneDigit(password)) {
                throw InvalidParameter(
                    message = Localization.NO_DIGIT_ERROR, errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            if (!passwordContainsAtLeastOneSpecialCharacters(password)) {
                throw InvalidParameter(
                    message = Localization.NO_SPECIAL_CHARACTER_ERROR,
                    errorCode = PASSWORD_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        fun isValidEmail(email: String): Boolean {
            if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"))) {
                throw InvalidParameter(
                    message = Localization.INVALID_EMAIL, errorCode = EMAIL_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            if (!phoneNumber.matches(Regex("^(\\+\\d{1,3}[- ]?)?\\d{10}$"))) {
                throw InvalidParameter(
                    message = Localization.INVALID_PHONE_NUMBER,
                    errorCode = PHONE_NUMBER_VALIDITY_ERROR_CODE
                )
            }

            return true
        }

        fun isValidLink(link: String, errorCode: String): Boolean {
            if (!link.matches(Regex("^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$"))) {
                throw InvalidParameter(message = Localization.INVALID_LINK, errorCode = errorCode)
            }

            return true
        }

        fun isValidDate(date: Long): Boolean {
            return Dates.convertLongToReadableDate(date).isNotBlank()
        }
    }

    object Conversion {
        fun getIntegerValue(value: String, length: Int): String {
            val absHashCode = value.hashCode().absoluteValue
            val numberStr = absHashCode.toString()
            val numDigits = numberStr.length
            if (numDigits < length) {
                val padding = "0".repeat(length - numDigits)
                return padding + numberStr
            } else {
                return numberStr.substring(0, 6)
            }
        }

        fun formatDecimalPlaces(value: Float): String {
            val valueStr = value.toString()
            val decimalIndex = valueStr.indexOf(".")
            val integerPart = valueStr.substring(0, decimalIndex)
            val decimalPart = valueStr.substring(decimalIndex + 1).run {
                if (length > 2) {
                    substring(0, 1)
                } else {
                    this
                }
            }

            return "$integerPart${Localization.FULL_STOP}$decimalPart"
        }

        fun combineStrings(str1: String, str2: String, length: Int): String {
            val eachLength = length.div(2)
            val firstPart = str1.substring(0, eachLength)
            val secondPart = str2.substring(eachLength)
            return "$firstPart$secondPart"
        }

        fun getAllTagsInTweet(tweet: String?): Map<String, Int> {
            if (tweet.isNullOrBlank()) {
                return emptyMap()
            }

            val regex = Regex(TAG_REGEX)
            val tags = regex.findAll(tweet).map { it.value }.toList()
            val tagMap = mutableMapOf<String, Int>()

            tags.forEach { tag ->
                val count = tagMap[tag] ?: 0
                tagMap[tag] = count + 1
            }

            return tagMap
        }

        fun getTweetWithTags(tweet: String): List<Pair<String, TextType>> {
            val regex = Regex(TAG_REGEX)
            val tagsDetails = regex.findAll(tweet).map {
                Triple(it.value, it.range.first, it.range.last + 1)
            }.toList()

            if (tagsDetails.isEmpty()) {
                return emptyList()
            }

            val subStrings = mutableListOf<Pair<String, TextType>>()

            for (i in tagsDetails.indices) {
                val tagDetails = tagsDetails[i]
                if (subStrings.isEmpty()) {
                    subStrings.add(Pair(tweet.substring(0, tagDetails.second), TextType.Normal))
                }
                subStrings.add(
                    Pair(
                        tweet.substring(tagDetails.second, tagDetails.third), TextType.Tag
                    )
                )

                if (i < tagsDetails.lastIndex) {
                    val nextTag = tagsDetails[i + 1]
                    subStrings.add(
                        Pair(
                            tweet.substring(tagDetails.third, nextTag.second), TextType.Normal
                        )
                    )
                }
            }

            if (tagsDetails.last().third < tweet.length) {
                subStrings.add(
                    Pair(
                        tweet.substring(tagsDetails.last().third), TextType.Normal
                    )
                )
            }

            return subStrings
        }
    }

    object Dates {
        fun convertLongToReadableDate(date: Long): String {
            val dateFormat = LocalDateTime.Format {
                monthName(
                    MonthNames.ENGLISH_FULL,
                )
                char(' ')
                dayOfMonth(padding = Padding.ZERO)
                char(',')
                char(' ')
                year()
            }

            val localDate =
                Instant.fromEpochSeconds(date).toLocalDateTime(TimeZone.currentSystemDefault())
            return dateFormat.format(localDate)
        }

        fun convertLongToReadableTime(date: Long): String {
            val dateFormat = LocalDateTime.Format {
                amPmHour(padding = Padding.NONE)
                char(':')
                minute(padding = Padding.ZERO)
                char(' ')
                amPmMarker(am = Localization.AM.lowercase(), pm = Localization.PM.lowercase())
            }

            val localDate =
                Instant.fromEpochSeconds(date).toLocalDateTime(TimeZone.currentSystemDefault())
            return dateFormat.format(localDate)
        }

        fun getCurrentTimeStamp(): Long = Clock.System.now().epochSeconds

        fun isFutureTimeStamp(timeStamp: Long) = Clock.System.now().epochSeconds < timeStamp

        fun getFutureTimeStamp(
            pollHour: Long,
            pollMinute: Long?,
            pollSeconds: Long?,
        ): Long {
            val now = Clock.System.now()
            val systemTZ = TimeZone.currentSystemDefault()
            var addedTime = now.plus(pollHour, DateTimeUnit.HOUR, systemTZ)
            pollMinute?.let { addedTime = addedTime.plus(it, DateTimeUnit.MINUTE, systemTZ) }
            pollSeconds?.let { addedTime = addedTime.plus(it, DateTimeUnit.SECOND, systemTZ) }

            return addedTime.epochSeconds
        }

        fun timeLeft(
            timestamp: Long,
        ): String {
            val now = Clock.System.now()
            val future = Instant.fromEpochSeconds(timestamp)
            val difference = future - now

            val days = difference.inWholeDays
            val hours = difference.inWholeHours % 24
            val minutes = difference.inWholeMinutes % 60

            val result = StringBuilder()

            if (days > 0) {
                if (days == 1L) {
                    result.append("$days ${Localization.DAY}")
                } else if (days > 1) {
                    result.append("$days ${Localization.DAYS}")
                }
            }

            if (hours > 0) {
                result.append(Localization.WHITE_SPACE)
                if (hours == 1L) {
                    result.append("$hours ${Localization.HOUR}")
                } else if (hours > 1) {
                    result.append("$hours ${Localization.HOURS}")
                }
            }

            if (minutes > 0) {
                result.append(Localization.WHITE_SPACE)
                if (minutes == 1L) {
                    result.append("$minutes ${Localization.MINUTE}")
                } else if (minutes > 1) {
                    result.append("$minutes ${Localization.MINUTES}")
                }
            }

            result.append(Localization.WHITE_SPACE)
            result.append(Localization.LEFT)

            return result.toString()
        }

        fun convertTimestampToTimeAgo(
            timestamp: Long,
        ): String {
            return TimeAgo.using(timestamp)
        }
    }
}