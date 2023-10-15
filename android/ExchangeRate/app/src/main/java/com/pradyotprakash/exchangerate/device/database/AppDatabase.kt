package com.pradyotprakash.exchangerate.device.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pradyotprakash.exchangerate.device.dao.AllCurrenciesDao
import com.pradyotprakash.exchangerate.device.dao.ExchangeRatesDao
import com.pradyotprakash.exchangerate.device.entities.AllCurrencies
import com.pradyotprakash.exchangerate.device.entities.ExchangeRate
import com.pradyotprakash.exchangerate.device.utils.MapStringDoubleConverter
import com.pradyotprakash.exchangerate.device.utils.MapStringStringConverter

@Database(entities = [ExchangeRate::class, AllCurrencies::class], version = 1, exportSchema = false)
@TypeConverters(MapStringDoubleConverter::class, MapStringStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exchangeRatesDao(): ExchangeRatesDao
    abstract fun allCurrenciesDao(): AllCurrenciesDao
}