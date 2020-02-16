package org.androidtown.linenote.features.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.androidtown.linenote.core.extension.empty
import org.androidtown.linenote.features.notepage.NotePageImageData
import org.androidtown.linenote.features.notepage.NotePageImageView

@Entity(tableName = "images")
data class NoteImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val noteID : Int,
    val image: String
){
    companion object {
        fun empty() = NoteImageEntity(-1, -1, String.empty())
    }

    fun toImages() = NotePageImageData(id,noteID,image)
}