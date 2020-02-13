package org.androidtown.linenote.features.notepage

import android.os.Bundle
import org.androidtown.linenote.R
import org.androidtown.linenote.core.extension.observe
import org.androidtown.linenote.core.extension.viewModel
import org.androidtown.linenote.core.platform.BaseFragment

class NotePageFragment : BaseFragment() {
    override fun layoutId() = R.layout.notepage_fragment
    private lateinit var notePageviewModel: NotePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        notePageviewModel = viewModel(viewModelFactory){
         //   observe(note,::renderNote)
        }
    }
/*
    private fun renderNote(notePageView: NotePageView){

    }*/
}
