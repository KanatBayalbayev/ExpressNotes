package com.qanatdev.expressnotes.domain

import androidx.lifecycle.LiveData

interface NotesListRepository {

    suspend fun addShopItem(note: Note)

    suspend fun deleteShopItem(note: Note)

    suspend fun editShopItem(note: Note)

    suspend fun getShopItem(noteId: Int): Note

    fun getShopList(): LiveData<List<Note>>
}


