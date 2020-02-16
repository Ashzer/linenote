package org.androidtown.linenote.features.notepage

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.notepage_fragment.*
import kotlinx.android.synthetic.main.notepage_recyclerview_note_item.*
import org.androidtown.linenote.R
import org.androidtown.linenote.core.extension.observe
import org.androidtown.linenote.core.extension.viewModel
import org.androidtown.linenote.core.navigation.Navigator
import org.androidtown.linenote.core.platform.BaseFragment
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
        }
    }

    override fun onResume() {
        super.onResume()
        initializeView()
    }

    private fun renderImage(notePageViews: List<NotePageImageView>?){
        notePageAdapter.collection = notePageViews.orEmpty()
    }

    private fun initializeView(){
        notepage_recyclerview_image.layoutManager = LinearLayoutManager(activity)
        notepage_recyclerview_image.adapter = notePageAdapter

        val id :Int = intent.getIntExtra("id",-1)
        notePageviewModel.loadImages(id)
    }
}
