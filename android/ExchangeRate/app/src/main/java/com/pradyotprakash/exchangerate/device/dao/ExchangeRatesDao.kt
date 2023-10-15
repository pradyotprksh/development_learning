package com.pradyotprakash.exchangerate.device.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pradyotprakash.exchangerate.device.entities.ExchangeRate

@Dao
interface ExchangeRatesDao {
    @Query("SELECT * FROM ExchangeRate WHERE countryCode == :countryCode")
    fun getRates(countryCode: String): ExchangeRate?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(exchangeRate: ExchangeRate)

    @Delete
    fun delete(exchangeRate: ExchangeRate)
}