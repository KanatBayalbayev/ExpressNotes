package com.qanatdev.expressnotes.domain


class DeleteNoteUseCase(private val notesListRepository: NotesListRepository) {

    suspend fun deleteShopItem(note: Note) {
        notesListRepository.deleteShopItem(note)
    }
}