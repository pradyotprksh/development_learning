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
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User")
    fun getUsers(): List<User>

    @Query("SELECT * FROM User WHERE username == :username")
    fun getUsersByUsername(username: String): List<User>

    @Query("SELECT * FROM User WHERE email_id == :emailId")
    fun getUsersByEmailId(emailId: String): List<User>
}