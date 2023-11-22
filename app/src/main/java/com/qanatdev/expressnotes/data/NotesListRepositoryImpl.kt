package com.qanatdev.expressnotes.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations;
import com.qanatdev.expressnotes.domain.Note
import com.qanatdev.expressnotes.domain.NotesListRepository
import javax.inject.Inject

class NotesListRepositoryImpl @Inject constructor(
    private val notesListDao: NotesListDao,
    private val mapper: NotesListMapper
) : NotesListRepository {


    override suspend fun addShopItem(note: Note) {
        notesListDao.addShopItem(mapper.mapEntityToDbModel(note))
    }

    override suspend fun deleteShopItem(note: Note) {
        notesListDao.deleteShopItem(note.id)
    }

    override suspend fun editShopItem(note: Note) {
        notesListDao.addShopItem(mapper.mapEntityToDbModel(note))
    }

    override suspend fun getShopItem(noteId: Int): Note {
        val dbModel = notesListDao.getShopItem(noteId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<Note>> = Transformations.map(
        notesListDao.getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }


}