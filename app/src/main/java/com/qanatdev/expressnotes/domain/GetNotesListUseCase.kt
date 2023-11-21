package com.qanatdev.expressnotes.domain

import androidx.lifecycle.LiveData


class GetNotesListUseCase(private val notesListRepository: NotesListRepository) {

    fun getShopList(): LiveData<List<Note>> {
        return notesListRepository.getShopList()
    }
}
