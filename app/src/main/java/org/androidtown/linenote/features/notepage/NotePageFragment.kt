package org.androidtown.linenote.features.notepage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.notepage_fragment.*
import kotlinx.android.synthetic.main.notepage_fragment.view.*
import kotlinx.android.synthetic.main.notepage_recyclerview_note_item.*
import org.androidtown.linenote.R
import org.androidtown.linenote.core.extension.observe
import org.androidtown.linenote.core.extension.viewModel
import org.androidtown.linenote.core.navigation.Navigator
import org.androidtown.linenote.core.platform.BaseFragment
import org.androidtown.linenote.features.linenote.LineNoteData
import org.androidtown.linenote.features.linenote.LineNoteView
import javax.inject.Inject

class NotePageFragment(private val intent : Intent) : BaseFragment() {
    override fun layoutId() = R.layout.notepage_fragment
    private lateinit var notePageviewModel: NotePageViewModel
    @Inject lateinit var navigator: Navigator
    @Inject lateinit var notePageAdapter: NotePageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        notePageviewModel = viewModel(viewModelFactory){
            observe(imageList,::renderImage)
            observe(note,::renderNote)
            /*
            observe(title,::renderTitle)
            observe(content,::renderContent)

             */
        }
    }

    override fun onResume() {
        super.onResume()
        initializeView()
    }

    private fun renderImage(notePageViews: List<NotePageImageView>?){
        notePageAdapter.collection = notePageViews.orEmpty()
    }

    private fun renderTitle(title: String?){
        notepage_textview_title.setText(title)

    }

    private fun renderContent(content: String?){
        notepage_textview_content.setText(content)
    }

    private fun renderNote(note:LineNoteData?){
        notepage_textview_title.setText(note?.title)
        notepage_textview_content.setText(note?.content)
    }

    private fun initializeView(){
        notepage_recyclerview_image.layoutManager = GridLayoutManager(activity,5)
        notepage_recyclerview_image.adapter = notePageAdapter

        val id :Int = intent.getIntExtra("id",-1)
        notePageviewModel.loadImages(id)
        notePageviewModel.loadNote(id)


        notepage_button_save.setOnClickListener {

            var title = notepage_textview_title.text.toString()
            var content = notepage_textview_content.text.toString()
            var thumbnail = notePageviewModel.note.value!!.thumbnail
            notePageviewModel.saveNote(LineNoteData(id,title,content,thumbnail))

            //notePageviewModel.saveNote(notePageviewModel.note.value!!)
            Toast.makeText(activity,"Note saved.",Toast.LENGTH_SHORT).show()
            navigator.showMain(activity!!)
        }
    }
}
