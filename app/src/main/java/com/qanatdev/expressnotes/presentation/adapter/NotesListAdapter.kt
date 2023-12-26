package com.qanatdev.expressnotes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.qanatdev.expressnotes.R
import com.qanatdev.expressnotes.databinding.NoteDisabledBinding
import com.qanatdev.expressnotes.databinding.NoteEnabledBinding
import com.qanatdev.expressnotes.domain.Note


class NotesListAdapter : ListAdapter<Note, NoteViewHolder>(NoteDiffCallback()) {

    var onNoteLongClickListener: ((Note) -> Unit)? = null
    var onNoteClickListener: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.note_disabled
            VIEW_TYPE_ENABLED -> R.layout.note_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        val binding = viewHolder.binding
        binding.root.setOnLongClickListener {
            onNoteLongClickListener?.invoke(note)
            true
        }
        binding.root.setOnClickListener {
            onNoteClickListener?.invoke(note)
        }
        when (binding) {
            is NoteDisabledBinding -> {
                binding.note = note
            }
            is NoteEnabledBinding -> {
                binding.note = note
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 30
    }
}
