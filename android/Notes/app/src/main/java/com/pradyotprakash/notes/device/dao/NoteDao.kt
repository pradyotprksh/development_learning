package com.pradyotprakash.notes.device.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pradyotprakash.notes.device.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM Note WHERE is_online == :isOnline")
    suspend fun getNotesIf(isOnline: Boolean): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg notes: Note)

    @Delete
    suspend fun delete(note: Note)
}