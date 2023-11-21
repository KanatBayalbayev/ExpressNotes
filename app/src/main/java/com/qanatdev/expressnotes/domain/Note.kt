package com.qanatdev.expressnotes.domain


data class Note(
    val name: String,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID
) {

    companion object {

        const val UNDEFINED_ID = 0
    }
}
