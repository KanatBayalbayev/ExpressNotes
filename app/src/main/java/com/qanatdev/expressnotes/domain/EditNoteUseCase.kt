package com.qanatdev.expressnotes.domain

import javax.inject.Inject


class EditNoteUseCase @Inject constructor(private val notesListRepository: NotesListRepository) {

    suspend fun editNote(note: Note) {
        notesListRepository.editNote(note)
    }
}
