package utils.timeago

import utils.Localization
import kotlin.math.roundToLong

internal enum class Periods(
    val text: String,
    private val predicate: DistancePredicate,
) {
    NOW(
        Localization.JUST_NOW,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 0L..(0.99).toLong()
            }
        },
    ),
    ONE_MINUTE_PAST(
        Localization.ONE_MINUTE_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance == 1L
            }
        },
    ),
    X_MINUTES_PAST(
        Localization.X_MINUTES_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 2..44
            }
        },
    ),
    ABOUT_AN_HOUR_PAST(
        Localization.ABOUT_AN_HOUR_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 45..89
            }
        },
    ),
    X_HOURS_PAST(
        Localization.X_HOURS_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 90..1439
            }
        },
    ),
    ONE_DAY_PAST(
        Localization.ONE_DAY_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 1440..2519
            }
        },
    ),
    X_DAYS_PAST(
        Localization.X_DAYS_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 2520..10079
            }
        },
    ),
    ONE_WEEK_PAST(
        Localization.ONE_WEEK_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 10080..20159
            }
        },
    ),
    X_WEEKS_PAST(
        Localization.X_WEEKS_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 20160..43199
            }
        },
    ),
    ABOUT_A_MONTH_PAST(
        Localization.ABOUT_A_MONTH_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 43200..86399
            }
        },
    ),
    X_MONTHS_PAST(
        Localization.X_MONTHS_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 86400..525599
            }
        },
    ),
    ABOUT_A_YEAR_PAST(
        Localization.ABOUT_A_YEAR_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 525600..655199
            }
        },
    ),
    OVER_A_YEAR_PAST(
        Localization.OVER_A_YEAR_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 655200..914399
            }
        },
    ),
    ALMOST_TWO_YEARS_PAST(
        Localization.ALMOST_TWO_YEARS_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance in 914400..1051199
            }
        },
    ),
    X_YEARS_PAST(
        Localization.X_YEARS_PAST,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return (distance / 525600).toFloat().roundToLong() > 1
            }
        },
    ),
    ONE_MINUTE_FUTURE(
        Localization.ONE_MINUTE_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance.toInt() == -1
            }
        },
    ),
    X_MINUTES_FUTURE(
        Localization.X_MINUTES_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -2 && distance >= -44
            }
        },
    ),
    ABOUT_AN_HOUR_FUTURE(
        Localization.ABOUT_AN_HOUR_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -45 && distance >= -89
            }
        },
    ),
    X_HOURS_FUTURE(
        Localization.X_HOURS_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -90 && distance >= -1439
            }
        },
    ),
    ONE_DAY_FUTURE(
        Localization.ONE_DAY_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -1440 && distance >= -2519
            }
        },
    ),
    X_DAYS_FUTURE(
        Localization.X_DAYS_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -2520 && distance >= -10079
            }
        },
    ),
    ONE_WEEK_FUTURE(
        Localization.ONE_WEEK_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -10080 && distance >= -20159
            }
        },
    ),
    X_WEEKS_FUTURE(
        Localization.X_WEEKS_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -20160 && distance >= -43199
            }
        },
    ),
    ABOUT_A_MONTH_FUTURE(
        Localization.ABOUT_A_MONTH_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -43200 && distance >= -86399
            }
        },
    ),
    X_MONTHS_FUTURE(
        Localization.X_MONTHS_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -86400 && distance >= -525599
            }
        },
    ),
    ABOUT_A_YEAR_FUTURE(
        Localization.ABOUT_A_YEAR_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -525600 && distance >= -655199
            }
        },
    ),
    OVER_A_YEAR_FUTURE(
        Localization.OVER_A_YEAR_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -655200 && distance >= -914399
            }
        },
    ),
    ALMOST_TWO_YEARS_FUTURE(
        Localization.ALMOST_TWO_YEARS_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return distance <= -914400 && distance >= -1051199
            }
        },
    ),
    X_YEARS_FUTURE(
        Localization.X_YEARS_FUTURE,
        object : DistancePredicate {
            override fun validateDistanceMinutes(distance: Long): Boolean {
                return (distance / 525600).toFloat().roundToLong() < -1
            }
        },
    );

    companion object {
        fun findByDistanceMinutes(distanceMinutes: Long): Periods? {
            val values = entries.toTypedArray()
            for (item in values) {
                val successful = item.predicate.validateDistanceMinutes(distanceMinutes)
                if (successful) {
                    return item
                }
            }
            return null
        }
    }
}