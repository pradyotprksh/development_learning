package com.pradyotprakash.exchangerate.device.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AllCurrencies(
    @PrimaryKey val time: Long,
    @ColumnInfo(name = "currencies") val currencies: Map<String, String>
)
