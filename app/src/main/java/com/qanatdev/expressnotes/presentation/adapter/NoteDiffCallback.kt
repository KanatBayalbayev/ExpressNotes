package com.qanatdev.expressnotes.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.qanatdev.expressnotes.domain.Note


class NoteDiffCallback: DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}