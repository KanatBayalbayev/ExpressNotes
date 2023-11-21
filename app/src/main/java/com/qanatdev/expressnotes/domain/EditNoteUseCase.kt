package com.qanatdev.expressnotes.domain


class EditNoteUseCase(private val notesListRepository: NotesListRepository) {

    suspend fun editShopItem(note: Note) {
        notesListRepository.editShopItem(note)
    }
}
