package org.androidtown.linenote.features.usecases

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import org.androidtown.linenote.features.entities.NoteEntity
import org.androidtown.linenote.features.entities.NoteImageEntity

@Dao
interface NoteDAO{
    @Query("SELECT * FROM notes")
    fun getAllNote(): List<NoteEntity>

    @Insert(onConflict = REPLACE)
    fun insertNote(vararg noteEntity: NoteEntity)

    @Insert(onConflict = REPLACE)
    fun insertNoteImage(vararg noteImageEntity: NoteImageEntity)

    @Query("SELECT * FROM images WHERE noteId = :noteId")
    fun getImagesByNoteId(noteId : Int): List<NoteImageEntity>
}