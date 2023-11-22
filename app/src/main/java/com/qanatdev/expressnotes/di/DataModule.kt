package com.qanatdev.expressnotes.di

import android.app.Application
import com.qanatdev.expressnotes.data.AppDatabase
import com.qanatdev.expressnotes.data.NotesListDao
import com.qanatdev.expressnotes.data.NotesListRepositoryImpl
import com.qanatdev.expressnotes.domain.NotesListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindNoteListRepository(impl: NotesListRepositoryImpl) : NotesListRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideNotesListDao(
            application: Application
        ): NotesListDao {
            return AppDatabase.getInstance(application).notesListDao()
        }
    }
}