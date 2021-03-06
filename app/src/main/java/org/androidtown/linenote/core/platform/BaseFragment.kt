package org.androidtown.linenote.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.androidtown.linenote.AndroidApplication
import org.androidtown.linenote.core.di.ApplicationComponent
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE){
        (activity?.application as AndroidApplication).appComponent
    }

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)

    open fun onBackPressed() {
    }

}