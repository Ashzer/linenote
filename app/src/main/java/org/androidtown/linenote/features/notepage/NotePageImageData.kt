package org.androidtown.linenote.features.notepage

import org.androidtown.linenote.core.extension.empty

data class NotePageImageData(val id:Int,
                             val noteId:Int,
                             val image:String){
    companion object {
        fun empty() = NotePageImageData(-1,-1,String.empty())
    }
}