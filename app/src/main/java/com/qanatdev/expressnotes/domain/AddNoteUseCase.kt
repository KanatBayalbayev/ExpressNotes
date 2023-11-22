package com.qanatdev.expressnotes.domain

import javax.inject.Inject


class AddNoteUseCase @Inject constructor(private val notesListRepository: NotesListRepository) {

    suspend fun addShopItem(note: Note) {
        notesListRepository.addShopItem(note)
    }
}