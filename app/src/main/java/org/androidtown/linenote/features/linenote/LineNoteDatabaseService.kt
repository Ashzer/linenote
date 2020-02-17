package org.androidtown.linenote.features.linenote

import org.androidtown.linenote.core.di.database.NoteDatabase
import org.androidtown.linenote.features.entities.NoteEntity
import javax.inject.Inject

class LineNoteDatabaseService

@Inject constructor(private val noteDB : NoteDatabase){
    fun notes() = noteDB.noteDAO().getAllNote()
    fun getImages(noteId : Int) = noteDB.noteDAO().getImagesByNoteId(noteId)
    fun getNoteById(id: Int) = noteDB.noteDAO().getNoteById(id)
    fun updateNote(noteEntity: NoteEntity) = noteDB.noteDAO().updateNote(noteEntity)
    //fun getFirstImageByNoteId(noteId : Int) = imageDB.noteImageDAO().getFirstImageByNoteId(noteId)
}