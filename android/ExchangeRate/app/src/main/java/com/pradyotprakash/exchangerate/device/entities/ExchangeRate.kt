package com.pradyotprakash.exchangerate.device.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExchangeRate(
    @PrimaryKey val countryCode: String,
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "rates") val exchangeRates: Map<String, Double>
)