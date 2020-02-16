package org.androidtown.linenote.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.androidtown.linenote.core.exception.Failure

abstract class  BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure){
        this.failure.value = failure
    }

}