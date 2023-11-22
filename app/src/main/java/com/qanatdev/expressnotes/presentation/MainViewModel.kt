package com.qanatdev.expressnotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qanatdev.expressnotes.domain.DeleteNoteUseCase
import com.qanatdev.expressnotes.domain.EditNoteUseCase
import com.qanatdev.expressnotes.domain.GetNotesListUseCase
import com.qanatdev.expressnotes.domain.Note
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getNotesListUseCase: GetNotesListUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : ViewModel() {


    val notesList = getNotesListUseCase.getNotesList()

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(note)
        }
    }

    fun changeEnableState(note: Note) {
        viewModelScope.launch {
            val newItem = note.copy(enabled = !note.enabled)
            editNoteUseCase.editNote(newItem)
        }
    }
}