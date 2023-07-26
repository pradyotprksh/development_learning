package com.pradyotprakash.pitgull.app.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatter {
    fun convertToReadableDate(dateTimeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())

        return try {
            val date = inputFormat.parse(dateTimeString)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            dateTimeString
        }
    }
}