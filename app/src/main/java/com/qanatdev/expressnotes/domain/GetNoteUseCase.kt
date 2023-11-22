package com.qanatdev.expressnotes.domain

import javax.inject.Inject


class GetNoteUseCase @Inject constructor(private val notesListRepository: NotesListRepository) {

    suspend fun getNote(noteId: Int): Note {
        return notesListRepository.getNote(noteId)
    }
}