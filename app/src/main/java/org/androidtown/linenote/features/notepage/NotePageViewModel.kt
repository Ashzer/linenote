package org.androidtown.linenote.features.notepage

import androidx.lifecycle.MutableLiveData
import org.androidtown.linenote.core.platform.BaseViewModel
import org.androidtown.linenote.features.linenote.LineNoteData
import org.androidtown.linenote.features.usecases.GetImages
import org.androidtown.linenote.features.usecases.GetNoteById
import org.androidtown.linenote.features.usecases.UpdateNote
import javax.inject.Inject

class NotePageViewModel
@Inject constructor(val getImages: GetImages , val getNoteById: GetNoteById, val updateNote: UpdateNote) : BaseViewModel() {
    var imageList: MutableLiveData<List<NotePageImageView>> = MutableLiveData()
    var note : MutableLiveData<LineNoteData> = MutableLiveData()

    /*var title : MutableLiveData<String> = MutableLiveData()
    var content : MutableLiveData<String> = MutableLiveData()
    var thumbnail : MutableLiveData<String> = MutableLiveData()*/
    fun loadNote(noteId: Int) = getNoteById(noteId) { it.fold(::handleFailure, ::handleNoteList)}
    fun loadImages(noteId: Int) =
        getImages(noteId) { it.fold(::handleFailure, ::handleNotePageImageList) }
    fun saveNote(lineNoteData: LineNoteData) = updateNote(lineNoteData)

    private fun handleNoteList(lineNoteData: LineNoteData){
        note.value = lineNoteData
        /*this.title.value = lineNoteData.title
        this.content.value = lineNoteData.content
        this.thumbnail.value = lineNoteData.thumbnail

         */
    }

    private fun handleNotePageImageList(notePageImageDatas: List<NotePageImageData>) {
        this.imageList.value = notePageImageDatas.map {
            NotePageImageView(
                it.id,
                it.noteId,
                it.image
            )
        }
    }

}
