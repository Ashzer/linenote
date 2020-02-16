package org.androidtown.linenote.features.linenote

import org.androidtown.linenote.core.di.database.NoteDatabase
import javax.inject.Inject

class LineNoteDatabaseService
@Inject constructor(private val noteDB : NoteDatabase){
    fun notes() = noteDB.noteDAO().getAllNote()
    fun getImages(noteId : Int) = noteDB.noteDAO().getImagesByNoteId(noteId)
    //fun getFirstImageByNoteId(noteId : Int) = imageDB.noteImageDAO().getFirstImageByNoteId(noteId)
}