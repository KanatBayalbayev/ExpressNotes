package com.qanatdev.expressnotes.data

import com.qanatdev.expressnotes.domain.Note
import javax.inject.Inject

class NotesListMapper @Inject constructor() {
    fun mapEntityToDbModel(note: Note) = NoteDbModel(
        id = note.id,
        name = note.name,
        enabled = note.enabled
    )

    fun mapDbModelToEntity(noteDbModel: NoteDbModel) = Note(
        id = noteDbModel.id,
        name = noteDbModel.name,
        enabled = noteDbModel.enabled
    )

    fun mapListDbModelToListEntity(list: List<NoteDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}