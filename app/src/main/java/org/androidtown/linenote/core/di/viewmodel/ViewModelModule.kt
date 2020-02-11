package org.androidtown.linenote.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.androidtown.linenote.features.linenote.LinenoteViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LinenoteViewModel::class)
    abstract fun bindsLinenoteViewModel(linenoteViewModel : LinenoteViewModel):ViewModel
}