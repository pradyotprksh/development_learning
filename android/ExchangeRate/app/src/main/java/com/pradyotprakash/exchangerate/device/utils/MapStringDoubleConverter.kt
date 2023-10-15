package com.pradyotprakash.exchangerate.device.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapStringDoubleConverter {
    @TypeConverter
    fun fromMap(map: Map<String, Double>): String {
        return Gson().toJson(map)
    }

    @TypeConverter
    fun toMap(json: String): Map<String, Double> {
        val type = object : TypeToken<Map<String, Double>>() {}.type
        return Gson().fromJson(json, type)
    }
}