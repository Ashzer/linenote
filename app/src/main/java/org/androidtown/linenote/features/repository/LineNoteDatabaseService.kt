package org.androidtown.linenote.features.repository

import org.androidtown.linenote.core.di.database.NoteDatabase
import org.androidtown.linenote.features.entities.NoteEntity
import org.androidtown.linenote.features.entities.NoteImageEntity
import javax.inject.Inject

class LineNoteDatabaseService

@Inject constructor(private val noteDB : NoteDatabase){
    fun notes() = noteDB.noteDAO().getAllNote()
    fun getImages(noteId : Int) = noteDB.noteDAO().getImagesByNoteId(noteId)
    fun getNoteById(id: Int) = noteDB.noteDAO().getNoteById(id)
    fun getFirstImages() = noteDB.noteDAO().getFirstImages()
    fun insertNote(noteEntity: NoteEntity) = noteDB.noteDAO().insertNote(noteEntity)
    fun updateNote(noteEntity: NoteEntity) = noteDB.noteDAO().updateNote(noteEntity)
    fun insertImage(noteImageEntity: NoteImageEntity) = noteDB.noteDAO().insertImage(noteImageEntity)
    fun deleteImage(noteImageEntity: NoteImageEntity) = noteDB.noteDAO().deleteNoteImage(noteImageEntity)

}