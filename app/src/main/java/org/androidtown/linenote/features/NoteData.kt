package org.androidtown.linenote.features

import org.androidtown.linenote.core.extension.empty
import org.androidtown.linenote.features.entities.NoteEntity

data class NoteData(
    val id: Int,
    val title: String,
    val content: String
) {
    companion object {
        fun empty() = NoteData(
            -1,
            String.empty(),
            String.empty()
        )
    }

    fun toNoteEntity() = NoteEntity(id, title, content)//,thumbnail)
}