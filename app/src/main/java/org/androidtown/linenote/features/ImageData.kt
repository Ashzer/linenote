package org.androidtown.linenote.features

import org.androidtown.linenote.core.extension.empty
import org.androidtown.linenote.features.entities.NoteImageEntity

data class ImageData(val id:Int,
                     val noteId:Int,
                     val image:String){
    companion object {
        fun empty() =
            ImageData(-1, -1, String.empty())
    }

    fun toNoteImageEntity() = NoteImageEntity(id,noteId,image)
}