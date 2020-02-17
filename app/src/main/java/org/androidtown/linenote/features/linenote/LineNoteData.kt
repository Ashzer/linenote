package org.androidtown.linenote.features.linenote

import org.androidtown.linenote.core.extension.empty
import org.androidtown.linenote.features.entities.NoteEntity

data class LineNoteData(val id : Int,
                        val title : String,
                        val content : String,
                        val thumbnail : String){
    companion object{
        fun empty() = LineNoteData(-1,String.empty(), String.empty(), String.empty())
    }

    fun toNoteEntity() = NoteEntity(id,title,content,thumbnail)
}