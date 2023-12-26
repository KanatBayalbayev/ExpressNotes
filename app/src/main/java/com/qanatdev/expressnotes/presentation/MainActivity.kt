package com.qanatdev.expressnotes.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.qanatdev.expressnotes.R
import com.qanatdev.expressnotes.databinding.ActivityMainBinding
import com.qanatdev.expressnotes.presentation.adapter.NotesListAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NoteFragment.OnEditingFinishedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var notesListAdapter: NotesListAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as NotesApplication).component
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launchWelcomeActivity()

        setupRecyclerView()

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.notesList.observe(this) {
            notesListAdapter.submitList(it)
            if (it.isNotEmpty()){
                binding.mainLogo?.visibility = View.GONE
            } else {
                binding.mainLogo?.visibility = View.VISIBLE
            }
        }


        binding.buttonAddNote.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = NoteActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(NoteFragment.newInstanceAddItem())
            }
        }

    }

    override fun onEditingFinished() {
        supportFragmentManager.popBackStack()
    }

    private fun launchWelcomeActivity(){
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val firstRun: Boolean = prefs.getBoolean(KEY_FIRST_RUN, true)

        if (firstRun) {
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
    }

    private fun isOnePaneMode(): Boolean {
        return binding.noteContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.note_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        with(binding.rvNotesList) {
            notesListAdapter = NotesListAdapter()
            adapter = notesListAdapter
            recycledViewPool.setMaxRecycledViews(
                NotesListAdapter.VIEW_TYPE_ENABLED,
                NotesListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                NotesListAdapter.VIEW_TYPE_DISABLED,
                NotesListAdapter.MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(binding.rvNotesList)
    }

    private fun setupSwipeListener(rvNotesList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = notesListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteNote(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvNotesList)
    }

    private fun setupClickListener() {
        notesListAdapter.onNoteClickListener = {
            if (isOnePaneMode()) {
                val intent = NoteActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(NoteFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setupLongClickListener() {
        notesListAdapter.onNoteLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    companion object{
        private val PREFS_NAME = "MyPrefsFile"
        private val KEY_FIRST_RUN = "firstRun"
    }
}