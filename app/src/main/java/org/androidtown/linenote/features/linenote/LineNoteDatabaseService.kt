package org.androidtown.linenote.features.linenote

import org.androidtown.linenote.core.di.database.NoteDatabase
import javax.inject.Inject

class LineNoteDatabaseService
@Inject constructor(val db : NoteDatabase){
    fun notes() = db.noteDAO().getAll()
}