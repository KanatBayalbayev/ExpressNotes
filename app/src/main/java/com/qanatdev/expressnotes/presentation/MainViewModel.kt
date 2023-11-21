package com.qanatdev.expressnotes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.qanatdev.expressnotes.data.NotesListRepositoryImpl
import com.qanatdev.expressnotes.domain.DeleteNoteUseCase
import com.qanatdev.expressnotes.domain.EditNoteUseCase
import com.qanatdev.expressnotes.domain.GetNotesListUseCase
import com.qanatdev.expressnotes.domain.Note
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NotesListRepositoryImpl(application)

    private val getShopListUseCase = GetNotesListUseCase(repository)
    private val deleteShopItemUseCase = DeleteNoteUseCase(repository)
    private val editShopItemUseCase = EditNoteUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(note: Note) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(note)
        }
    }

    fun changeEnableState(note: Note) {
        viewModelScope.launch {
            val newItem = note.copy(enabled = !note.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}