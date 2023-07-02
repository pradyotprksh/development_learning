package com.pradyotprakash.notes.device.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val noteId: String,
    @ColumnInfo(name = "created_on") val createdOn: Int,
    @ColumnInfo(name = "is_online") val isOnline: Boolean,
)
