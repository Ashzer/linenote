package org.androidtown.linenote.features.notepage

import androidx.lifecycle.MutableLiveData
import org.androidtown.linenote.core.platform.BaseViewModel
import javax.inject.Inject

class NotePageViewModel
@Inject constructor(): BaseViewModel() {
    var note: MutableLiveData<NotePageView> = MutableLiveData()
}
