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
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM Note WHERE is_online == :isOnline")
    fun getNotesIf(isOnline: Boolean): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg notes: Note)

    @Delete
    fun delete(note: Note)
}