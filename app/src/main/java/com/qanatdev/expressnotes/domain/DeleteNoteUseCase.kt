package com.qanatdev.expressnotes.domain

import javax.inject.Inject


class DeleteNoteUseCase @Inject constructor(private val notesListRepository: NotesListRepository) {

    suspend fun deleteShopItem(note: Note) {
        notesListRepository.deleteShopItem(note)
    }
}