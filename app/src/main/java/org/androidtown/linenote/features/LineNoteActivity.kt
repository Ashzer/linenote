package org.androidtown.linenote.features

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import java.util.*
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

        if(ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.CAMERA),0)
        }

/*
        runBlocking {
            GlobalScope.launch(Dispatchers.IO) {
                noteDB.noteDAO().insertNote(NoteEntity(1, "title 11", "content111"))//,"https://images.unsplash.com/photo-1581009502023-e9c081762f2b?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertNote(NoteEntity(2, "title 22", "content222"))//,"https://images.unsplash.com/photo-1580309692531-e7f94f86ca28?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertNote(NoteEntity(3, "title 33", "content333"))//,""))
                noteDB.noteDAO().insertNote(NoteEntity(4, "title 44", "content444"))//,"https://images.unsplash.com/photo-1579564622266-34501e71685f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                //noteDB.noteDAO().insertImage(NoteImageEntity(1,1,""))
                noteDB.noteDAO().insertImage(NoteImageEntity(1,1,"https://images.unsplash.com/photo-1581009502023-e9c081762f2b?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(2,1,"https://images.unsplash.com/photo-1579478656262-92412544f2b8?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(3,1,"https://images.unsplash.com/photo-1581552085566-5668a71eda28?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(4,1,"https://images.unsplash.com/photo-1580872395901-dc618b4b317b?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(5,1,"https://images.unsplash.com/photo-1579460372806-f2ef79336b10?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(6,1,"https://images.unsplash.com/photo-1579199339857-e49838357bad?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(7,1,"https://images.unsplash.com/photo-1580247817119-c6cb496270a4?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(8,1,"https://images.unsplash.com/photo-1579155998883-ed18bd27ab58?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(9,1,"https://images.unsplash.com/photo-1580569615934-f535711a7fdd?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(10,1,"https://images.unsplash.com/photo-1579384264577-79580c9d3a36?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                //noteDB.noteDAO().insertImage(NoteImageEntity(12,2,""))
                noteDB.noteDAO().insertImage(NoteImageEntity(11,2,"https://images.unsplash.com/photo-1580309692531-e7f94f86ca28?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(12,2,"https://images.unsplash.com/photo-1579689183178-849b86591a05?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(13,3,"NoNoteImage"))
                //noteDB.noteDAO().insertImage(NoteImageEntity(16,4,""))
                noteDB.noteDAO().insertImage(NoteImageEntity(14,4,"https://images.unsplash.com/photo-1579564622266-34501e71685f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(15,4,"https://images.unsplash.com/photo-1580565996832-60ccfdf1a755?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
                noteDB.noteDAO().insertImage(NoteImageEntity(16,4,"https://images.unsplash.com/photo-1579225923028-8c1383d22395?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max"))
            }
        }*/
    }

    override var layout = R.layout.linenote_activity
    override var fragmentId = R.id.lineNote_flo_container
    override fun fragment() = LineNoteFragment()
}