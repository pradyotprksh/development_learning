package utils.timeago

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import utils.Localization
import kotlin.math.abs
import kotlin.math.roundToLong
import kotlin.time.DurationUnit

/**
 * The code has been used from https://github.com/marlonlom/timeago/tree/master
 *
 * Since the library is not being used by the KMP, I took the required code from
 * the codebase and used it as per my requirement.
 *
 * The code has been modified to suit my needs.
 */
object TimeAgo {
    fun using(time: Long): String {
        val dim = getTimeDistanceInMinutes(time)
        val timeAgo = buildTimeagoText(dim)
        return timeAgo.toString()
    }

    private fun buildTimeagoText(dim: Long): StringBuilder {
        val timeAgo = StringBuilder()

        val foundTimePeriod = Periods.findByDistanceMinutes(dim)
        if (foundTimePeriod != null) {
            val periodKey = foundTimePeriod.text
            when (foundTimePeriod) {
                Periods.X_MINUTES_PAST -> timeAgo.append(Localization.format(periodKey, dim))
                Periods.X_HOURS_PAST -> {
                    val hours = (dim / 60f).roundToLong()
                    val xHoursText = handlePeriodKeyAsPlural(
                        Localization.ABOUT_AN_HOUR_PAST, periodKey, hours.toInt()
                    )
                    timeAgo.append(xHoursText)
                }

                Periods.X_DAYS_PAST -> {
                    val days = (dim / 1440f).roundToLong()
                    val xDaysText = handlePeriodKeyAsPlural(
                        Localization.ONE_DAY_PAST, periodKey, days.toInt()
                    )
                    timeAgo.append(xDaysText)
                }

                Periods.X_WEEKS_PAST -> {
                    val weeks = (dim / 10080f).roundToLong()
                    val xWeeksText = handlePeriodKeyAsPlural(
                        Localization.ONE_WEEK_PAST, periodKey, weeks.toInt()
                    )
                    timeAgo.append(xWeeksText)
                }

                Periods.X_MONTHS_PAST -> {
                    val months = (dim / 43200f).roundToLong()
                    val xMonthsText = handlePeriodKeyAsPlural(
                        Localization.ABOUT_A_MONTH_PAST, periodKey, months.toInt()
                    )
                    timeAgo.append(xMonthsText)
                }

                Periods.X_YEARS_PAST -> {
                    val years = (dim / 525600f).roundToLong()
                    timeAgo.append(Localization.format(periodKey, years))
                }

                Periods.X_MINUTES_FUTURE -> timeAgo.append(
                    Localization.format(
                        periodKey, abs(dim.toFloat())
                    )
                )

                Periods.X_HOURS_FUTURE -> {
                    val hours1 = abs((dim / 60f).roundToLong())
                    val yHoursText = if (hours1.toInt() == 24) Localization.ONE_DAY_FUTURE
                    else handlePeriodKeyAsPlural(
                        Localization.ABOUT_AN_HOUR_FUTURE, periodKey, hours1.toInt()
                    )
                    timeAgo.append(yHoursText)
                }

                Periods.X_DAYS_FUTURE -> {
                    val days1 = abs((dim / 1440f).roundToLong())
                    val yDaysText = handlePeriodKeyAsPlural(
                        Localization.ONE_DAY_FUTURE, periodKey, days1.toInt()
                    )
                    timeAgo.append(yDaysText)
                }

                Periods.X_WEEKS_FUTURE -> {
                    val weeks1 = abs((dim / 10080f).roundToLong())
                    val yWeeksText = handlePeriodKeyAsPlural(
                        Localization.ONE_WEEK_FUTURE, periodKey, weeks1.toInt()
                    )
                    timeAgo.append(yWeeksText)
                }

                Periods.X_MONTHS_FUTURE -> {
                    val months1 = abs((dim / 43200f).roundToLong())
                    val yMonthsText = if (months1.toInt() == 12) Localization.ABOUT_A_YEAR_FUTURE
                    else handlePeriodKeyAsPlural(
                        Localization.ABOUT_A_MONTH_FUTURE, periodKey, months1.toInt()
                    )
                    timeAgo.append(yMonthsText)
                }

                Periods.X_YEARS_FUTURE -> {
                    val years1 = abs((dim / 525600f).roundToLong())
                    timeAgo.append(Localization.format(periodKey, years1))
                }

                else -> timeAgo.append(periodKey)
            }
        }

        return timeAgo
    }

    private fun handlePeriodKeyAsPlural(
        periodKey: String,
        pluralKey: String,
        value: Int,
    ) = if (value == 1) Localization.format(periodKey, value) else Localization.format(
        pluralKey, value
    )

    private fun getTimeDistanceInMinutes(timestamp: Long): Long {
        val now = Clock.System.now()
        val time = Instant.fromEpochSeconds(timestamp)
        val duration = now - time
        return duration.toLong(DurationUnit.MINUTES)
    }
}