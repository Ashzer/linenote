package org.androidtown.linenote.features.notepage

import androidx.lifecycle.MutableLiveData
import org.androidtown.linenote.core.platform.BaseViewModel
import org.androidtown.linenote.features.usecases.GetImages
import javax.inject.Inject

class NotePageViewModel
@Inject constructor(val getImages: GetImages) : BaseViewModel() {
    var imageList: MutableLiveData<List<NotePageImageView>> = MutableLiveData()

    fun loadImages(noteId: Int) =
        getImages(noteId) { it.fold(::handleFailure, ::handleNotePageImageList) }

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
