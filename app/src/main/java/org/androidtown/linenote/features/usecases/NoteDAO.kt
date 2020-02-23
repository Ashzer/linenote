package org.androidtown.linenote.features.usecases

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import org.androidtown.linenote.core.interactor.UseCase.None
import org.androidtown.linenote.features.entities.NoteEntity
import org.androidtown.linenote.features.entities.NoteImageEntity

@Dao
interface NoteDAO {

    @Query("SELECT * FROM notes")
    fun getAllNote(): List<NoteEntity>

    @Query(" SELECT note.id AS id, MIN(image.id) AS noteID, image.image AS image FROM notes AS note INNER JOIN images AS image ON note.id = image.noteID GROUP BY note.id ")
    fun getFirstImages(): List<NoteImageEntity>

    @Insert(onConflict = REPLACE)
    fun insertNote(noteEntity: NoteEntity): Long

    @Insert(onConflict = REPLACE)
    fun insertImage(vararg noteImageEntity: NoteImageEntity)

    @Update
    fun updateNote(vararg noteEntity: NoteEntity)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity

    @Query("SELECT * FROM images WHERE noteId = :noteId")
    fun getImagesByNoteId(noteId: Int): List<NoteImageEntity>

    @Query("DELETE FROM notes WHERE id = :noteId")
    fun deleteNoteByNoteId(noteId: Int)

    @Query("DELETE FROM images WHERE noteid = :noteId")
    fun deleteImagesByNoteId(noteId: Int)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNoteImage(noteImageEntity: NoteImageEntity)
}