package com.qanatdev.expressnotes.presentation

import android.app.Application
import com.qanatdev.expressnotes.di.DaggerApplicationComponent

class NotesApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}