package org.androidtown.linenote.features.linenote

import org.androidtown.linenote.core.extension.empty

data class LineNoteData(val id : Int,
                        val image : String,
                        val title : String,
                        val content : String){
    companion object{
        fun empty() = LineNoteData(-1,String.empty(),String.empty(), String.empty())
    }
}