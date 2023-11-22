package com.qanatdev.expressnotes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qanatdev.expressnotes.data.NotesListRepositoryImpl
import com.qanatdev.expressnotes.domain.DeleteNoteUseCase
import com.qanatdev.expressnotes.domain.EditNoteUseCase
import com.qanatdev.expressnotes.domain.GetNotesListUseCase
import com.qanatdev.expressnotes.domain.Note
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetNotesListUseCase,
    private val deleteShopItemUseCase: DeleteNoteUseCase,
    private val editShopItemUseCase: EditNoteUseCase
) : ViewModel() {


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