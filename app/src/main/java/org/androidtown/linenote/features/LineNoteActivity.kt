package org.androidtown.linenote.features

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.androidtown.linenote.R
import org.androidtown.linenote.core.di.database.NoteDatabase
import org.androidtown.linenote.core.navigation.Navigator
import org.androidtown.linenote.core.platform.BaseActivity
import org.androidtown.linenote.features.entities.NoteEntity
import org.androidtown.linenote.features.entities.NoteImageEntity
import org.androidtown.linenote.features.linenote.LineNoteFragment
import javax.inject.Inject

class LineNoteActivity : BaseActivity() {
    @Inject
    lateinit var noteDB: NoteDatabase
    @Inject
    lateinit var navigator: Navigator

    companion object {
        fun callingIntent(context: Context) = Intent(context, LineNoteActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        runBlocking {
            GlobalScope.launch(Dispatchers.IO) {
                noteDB.noteDAO().insertNote(NoteEntity(1, "title 11", "content111","thumb111"))
                noteDB.noteDAO().insertNote(NoteEntity(2, "title 22", "content222","thumb222"))
                noteDB.noteDAO().insertNote(NoteEntity(3, "title 33", "content333","thumb333"))
                noteDB.noteDAO().insertNote(NoteEntity(4, "title 44", "content444","thumb444"))
                noteDB.noteDAO().insertNoteImage(NoteImageEntity(1,1,"http://placehold.it/200x200/aaa/ggg&text=1page 1st"))
                noteDB.noteDAO().insertNoteImage(NoteImageEntity(2,1,"http://placehold.it/200x200/aaa/ggg&text=1page 2nd"))
                noteDB.noteDAO().insertNoteImage(NoteImageEntity(3,1,"http://placehold.it/200x200/aaa/ggg&text=1page 3rd"))
                noteDB.noteDAO().insertNoteImage(NoteImageEntity(4,2,"http://placehold.it/200x200/aaa/ggg&text=2page 1st"))
                noteDB.noteDAO().insertNoteImage(NoteImageEntity(5,2,"http://placehold.it/200x200/aaa/ggg&text=2page 2nd"))
                noteDB.noteDAO().insertNoteImage(NoteImageEntity(6,4,"http://placehold.it/200x200/aaa/ggg&text=4page 1st"))
                noteDB.noteDAO().insertNoteImage(NoteImageEntity(7,4,"http://placehold.it/200x200/aaa/ggg&text=4page 2nd"))
                noteDB.noteDAO().insertNoteImage(NoteImageEntity(8,4,"http://placehold.it/200x200/aaa/ggg&text=4page 3rd"))

            }
        }
    }

    override var layout = R.layout.linenote_activity
    override var fragmentId = R.id.lineNote_flo_container
    override fun fragment() = LineNoteFragment()
}
