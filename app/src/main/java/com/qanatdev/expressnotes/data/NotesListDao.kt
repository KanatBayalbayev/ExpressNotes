package com.qanatdev.expressnotes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesListDao {

    @Query("SELECT * FROM notes")
    fun getShopList(): LiveData<List<NoteDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDbModel: NoteDbModel)

    @Query("DELETE FROM notes WHERE id=:noteId")
    suspend fun deleteShopItem(noteId: Int)

    @Query("SELECT * FROM notes WHERE id=:noteId LIMIT 1")
    suspend fun getShopItem(noteId: Int): NoteDbModel
}