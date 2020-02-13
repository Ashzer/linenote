package org.androidtown.linenote.features.linenote

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.handleCoroutineException
import org.androidtown.linenote.core.interactor.UseCase.None
import org.androidtown.linenote.core.platform.BaseViewModel
import org.androidtown.linenote.features.usecases.GetNotes
import javax.inject.Inject

class LineNoteViewModel
@Inject constructor(private val getNotes: GetNotes) : BaseViewModel() {
    var noteList: MutableLiveData<List<LineNoteView>> = MutableLiveData()

    fun loadNotes() = getNotes(None()){ it.fold(::handleFailure,::handleLineNoteDataList) }

    private fun handleLineNoteDataList(lineNoteDatas: List<LineNoteData>) {
        this.noteList.value = lineNoteDatas.map {
            LineNoteView(
                it.id,
                it.image,
                it.title,
                it.content
            )
        }
    }
}
