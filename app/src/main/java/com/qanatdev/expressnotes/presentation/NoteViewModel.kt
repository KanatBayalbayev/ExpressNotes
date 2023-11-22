package com.qanatdev.expressnotes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qanatdev.expressnotes.domain.AddNoteUseCase
import com.qanatdev.expressnotes.domain.EditNoteUseCase
import com.qanatdev.expressnotes.domain.GetNoteUseCase
import com.qanatdev.expressnotes.domain.Note
import kotlinx.coroutines.launch
import javax.inject.Inject


class NoteViewModel @Inject constructor(
    private val getNotesListUseCase: GetNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : ViewModel() {

   
    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note>
        get() = _note

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getNote(noteId: Int) {
        viewModelScope.launch {
            val item = getNotesListUseCase.getNote(noteId)
            _note.value = item
        }
    }

    fun addNote(inputName: String?) {
        val name = parseName(inputName)
        val fieldsValid = validateInput(name)
        if (fieldsValid) {
            viewModelScope.launch {
                val shopItem = Note(name, true)
                addNoteUseCase.addNote(shopItem)
                finishWork()
            }
        }
    }

    fun editNote(inputName: String?) {
        val name = parseName(inputName)
        val fieldsValid = validateInput(name)
        if (fieldsValid) {
            _note.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name)
                    editNoteUseCase.editNote(item)
                    finishWork()
                }
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }


    private fun validateInput(name: String): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }


    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}
