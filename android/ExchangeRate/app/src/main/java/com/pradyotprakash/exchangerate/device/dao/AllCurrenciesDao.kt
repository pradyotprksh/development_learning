package com.pradyotprakash.exchangerate.device.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pradyotprakash.exchangerate.device.entities.AllCurrencies

@Dao
interface AllCurrenciesDao {
    @Query("SELECT * FROM AllCurrencies")
    fun getCurrencies(): AllCurrencies?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(allCurrencies: AllCurrencies)

    @Delete
    fun delete(allCurrencies: AllCurrencies)
}