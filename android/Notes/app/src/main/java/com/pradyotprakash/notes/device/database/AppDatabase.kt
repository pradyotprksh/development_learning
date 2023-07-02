package com.pradyotprakash.notes.device.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pradyotprakash.notes.device.dao.NoteDao
import com.pradyotprakash.notes.device.entity.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}