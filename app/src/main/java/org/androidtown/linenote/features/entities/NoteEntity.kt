package org.androidtown.linenote.features.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.androidtown.linenote.core.extension.empty
import org.androidtown.linenote.features.NoteData

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String
) {
    companion object {
        fun empty() = NoteEntity(-1, String.empty(), String.empty())
    }

    fun toNotes() = NoteData(id,title,content)
}