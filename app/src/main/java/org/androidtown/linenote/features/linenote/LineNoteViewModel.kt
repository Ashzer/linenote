package org.androidtown.linenote.features.linenote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.androidtown.linenote.core.interactor.UseCase.None
import org.androidtown.linenote.core.platform.BaseViewModel
import org.androidtown.linenote.features.ImageData
import org.androidtown.linenote.features.NoteData
import org.androidtown.linenote.features.usecases.GetFirstImages
import org.androidtown.linenote.features.usecases.GetNotes
import javax.inject.Inject

class LineNoteViewModel
@Inject constructor(
    private val getNotes: GetNotes,
    private val getFirstImages: GetFirstImages
) : BaseViewModel() {
    var noteList: MutableLiveData<List<LineNoteView>> = MutableLiveData()
    var thumbnailList : MutableLiveData<List<LineNoteImageView>> = MutableLiveData()

    fun loadNotes() = getNotes(None()) { it.fold(::handleFailure, ::handleLineNoteDataList) }

    fun getThumbnail() = getFirstImages(None()) { it.fold(::handleFailure, ::handleImage) }

    private fun handleImage(imageData: List<ImageData>){
        this.thumbnailList.value = imageData.map{
            LineNoteImageView(
                it.id,
                it.noteId,
                it.image
            )
        }
      //  Log.d("image","thumbnail ${imageData.size}")
    }

    private fun handleLineNoteDataList(noteDatas: List<NoteData>) {
        this.noteList.value = noteDatas.map {
            LineNoteView(
                it.id,
                it.title,
                it.content
            //    it.thumbnail
            )
        }
      //  Log.d("image","note ${noteDatas.size}")
    }
}
