package com.pradyotprakash.notes.device.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pradyotprakash.notes.device.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM User WHERE username == :username")
    suspend fun getUserDetails(username: String): User

    @Query("SELECT * FROM User")
    suspend fun getUsers(): List<User>
}