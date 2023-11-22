package com.qanatdev.expressnotes.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations;
import com.qanatdev.expressnotes.domain.Note
import com.qanatdev.expressnotes.domain.NotesListRepository
import javax.inject.Inject

class NotesListRepositoryImpl @Inject constructor(
    private val notesListDao: NotesListDao,
    private val mapper: NotesListMapper
) : NotesListRepository {


    override suspend fun addNote(note: Note) {
        notesListDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun deleteNote(note: Note) {
        notesListDao.deleteNote(note.id)
    }

    override suspend fun editNote(note: Note) {
        notesListDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun getNote(noteId: Int): Note {
        val dbModel = notesListDao.getNote(noteId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getNotesList(): LiveData<List<Note>> = Transformations.map(
        notesListDao.getNotesList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }


}