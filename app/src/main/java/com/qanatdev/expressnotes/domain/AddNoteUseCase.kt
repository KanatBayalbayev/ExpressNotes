package com.qanatdev.expressnotes.domain


class AddNoteUseCase(private val notesListRepository: NotesListRepository) {

    suspend fun addShopItem(note: Note) {
        notesListRepository.addShopItem(note)
    }
}