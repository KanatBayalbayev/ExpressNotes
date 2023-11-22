package com.qanatdev.expressnotes.di

import androidx.lifecycle.ViewModel
import com.qanatdev.expressnotes.presentation.MainViewModel
import com.qanatdev.expressnotes.presentation.NoteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    fun bindNoteViewModel(noteViewModel: NoteViewModel): ViewModel
}