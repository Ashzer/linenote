package org.androidtown.linenote.features.usecases
/*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import org.androidtown.linenote.features.entities.NoteImageEntity

@Dao
interface NoteImageDAO{
    @Query("SELECT * FROM images")
    fun getAll(): List<NoteImageEntity>

    @Insert(onConflict = REPLACE)
    fun insert(vararg noteImageEntity: NoteImageEntity)

    @Query("SELECT MIN('id') FROM images WHERE noteID = :noteId")
    fun getFirstImageByNoteId(noteId: Int): String
}
*/