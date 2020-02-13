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
import org.androidtown.linenote.core.extension.empty
import org.androidtown.linenote.core.navigation.Navigator
import org.androidtown.linenote.core.platform.BaseActivity
import org.androidtown.linenote.features.entities.NoteEntity
import org.androidtown.linenote.features.linenote.LineNoteFragment
import javax.inject.Inject

class LineNoteActivity : BaseActivity() {
    @Inject lateinit var db : NoteDatabase
    @Inject lateinit var navigator: Navigator

    companion object {
        fun callingIntent(context: Context) = Intent(context, LineNoteActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        runBlocking {
            GlobalScope.launch(Dispatchers.IO) {
                db.noteDAO().insert(NoteEntity(1, String.empty(), "title 11", "content111"))
                db.noteDAO().insert(NoteEntity(2, String.empty(), "title 22", "content222"))
                db.noteDAO().insert(NoteEntity(3, String.empty(), "title 33", "content333"))
                db.noteDAO().insert(NoteEntity(4, String.empty(), "title 44", "content444"))
            }
        }
    }

    override var layout = R.layout.linenote_activity
    override var fragmentId = R.id.lineNote_flo_container
    override fun fragment() = LineNoteFragment()
}
