package com.qanatdev.expressnotes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesListDao {

    @Query("SELECT * FROM notes")
    fun getNotesList(): LiveData<List<NoteDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteDbModel: NoteDbModel)

    @Query("DELETE FROM notes WHERE id=:noteId")
    suspend fun deleteNote(noteId: Int)

    @Query("SELECT * FROM notes WHERE id=:noteId LIMIT 1")
    suspend fun getNote(noteId: Int): NoteDbModel
}