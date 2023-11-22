package com.qanatdev.expressnotes.domain

import javax.inject.Inject


class AddNoteUseCase @Inject constructor(private val notesListRepository: NotesListRepository) {

    suspend fun addNote(note: Note) {
        notesListRepository.addNote(note)
    }
}