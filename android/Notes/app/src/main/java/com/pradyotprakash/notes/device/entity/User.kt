package com.pradyotprakash.notes.device.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val username: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email_id") val emailId: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "created_on") val createdOn: Int,
)