package org.androidtown.linenote.core.di.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.androidtown.linenote.features.entities.NoteEntity
import org.androidtown.linenote.features.entities.NoteImageEntity
import org.androidtown.linenote.features.usecases.NoteDAO

@Database(entities=[NoteEntity::class , NoteImageEntity::class], version = 1 , exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDAO() : NoteDAO
}