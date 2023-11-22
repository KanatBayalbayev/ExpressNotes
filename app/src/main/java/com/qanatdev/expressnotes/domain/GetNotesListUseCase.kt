package com.qanatdev.expressnotes.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject


class GetNotesListUseCase @Inject constructor(private val notesListRepository: NotesListRepository) {

    fun getShopList(): LiveData<List<Note>> {
        return notesListRepository.getShopList()
    }
}
