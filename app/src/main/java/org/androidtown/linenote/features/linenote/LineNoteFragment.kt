package org.androidtown.linenote.features.linenote

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.linenote_fragment.*
import org.androidtown.linenote.R
import org.androidtown.linenote.core.di.database.NoteDatabase
import org.androidtown.linenote.core.extension.observe
import org.androidtown.linenote.core.extension.viewModel
import org.androidtown.linenote.core.navigation.Navigator
import org.androidtown.linenote.core.platform.BaseFragment
import javax.inject.Inject


class LineNoteFragment : BaseFragment() {
    override fun layoutId() = R.layout.linenote_fragment

    private lateinit var lineNoteViewModel : LineNoteViewModel
    @Inject lateinit var navigator : Navigator
    @Inject lateinit var noteDB: NoteDatabase
    @Inject lateinit var lineNoteAdapter: LineNoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        lineNoteViewModel = viewModel(viewModelFactory){
            observe(noteList,::renderNoteList)
            observe(thumbnailList,::renderThumbnailList)
            //failure(failure, ::handleFailure)
        }
    }

    override fun onResume() {
        super.onResume()
        initializeView()
    }

    private fun renderNoteList(lineNoteViews: List<LineNoteView>?){
        lineNoteAdapter.noteCollection = lineNoteViews.orEmpty()
    }

    private fun renderThumbnailList(lineNoteImageViews: List<LineNoteImageView>?){
            lineNoteAdapter.imageCollection = lineNoteImageViews.orEmpty()
    }

    private fun initializeView(){
        lineNote_recyclerview_note.layoutManager = LinearLayoutManager(activity)
        lineNote_recyclerview_note.adapter = lineNoteAdapter

        lineNoteAdapter.clickListener = {id->
            navigator.showNote(activity!!,id)
        }

        lineNote_floatingbtn_add.setOnClickListener {
            navigator.showNote(activity!!,-1)
        }

        lineNoteViewModel.loadNotes()
        lineNoteViewModel.getThumbnail()
    }

}

