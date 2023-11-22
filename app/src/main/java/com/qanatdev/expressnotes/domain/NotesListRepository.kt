package com.qanatdev.expressnotes.domain

import androidx.lifecycle.LiveData

interface NotesListRepository {

    suspend fun addNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun editNote(note: Note)

    suspend fun getNote(noteId: Int): Note

    fun getNotesList(): LiveData<List<Note>>
}


