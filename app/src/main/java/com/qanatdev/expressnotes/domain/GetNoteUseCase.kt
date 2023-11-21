package com.qanatdev.expressnotes.domain


class GetNoteUseCase(private val notesListRepository: NotesListRepository) {

    suspend fun getShopItem(noteId: Int): Note {
        return notesListRepository.getShopItem(noteId)
    }
}