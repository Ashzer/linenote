package org.androidtown.linenote.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.androidtown.linenote.features.linenote.LineNoteViewModel
import org.androidtown.linenote.features.notepage.NotePageViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LineNoteViewModel::class)
    abstract fun bindLineNoteViewModel(lineNoteViewModel: LineNoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotePageViewModel::class)
    abstract fun bindNotePageViewModel(notePageViewModel: NotePageViewModel):ViewModel

}