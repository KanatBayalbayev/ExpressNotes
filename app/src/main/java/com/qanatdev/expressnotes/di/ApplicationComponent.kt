package com.qanatdev.expressnotes.di

import android.app.Application
import com.qanatdev.expressnotes.presentation.MainActivity
import com.qanatdev.expressnotes.presentation.NoteFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(noteFragment: NoteFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}