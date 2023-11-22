package com.qanatdev.expressnotes.domain

import javax.inject.Inject


class GetNoteUseCase @Inject constructor(private val notesListRepository: NotesListRepository) {

    suspend fun getShopItem(noteId: Int): Note {
        return notesListRepository.getShopItem(noteId)
    }
}