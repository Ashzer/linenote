package org.androidtown.linenote.features.usecases

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import org.androidtown.linenote.features.entities.NoteEntity

@Dao
interface NoteDAO{
    @Query("SELECT * FROM notes")
    fun getAll(): List<NoteEntity>
    
    @Insert(onConflict = REPLACE)
    fun insert(vararg noteEntity: NoteEntity)

    @Update
    fun update(vararg noteEntity: NoteEntity)
}