package org.androidtown.linenote.core.di


import dagger.Component
import org.androidtown.linenote.AndroidApplication
import org.androidtown.linenote.core.di.viewmodel.ViewModelModule
import org.androidtown.linenote.features.LineNoteActivity
import org.androidtown.linenote.features.NotePageActivity
import org.androidtown.linenote.features.linenote.LineNoteFragment
import org.androidtown.linenote.features.notepage.NotePageFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)

    fun inject(lineNoteActivity: LineNoteActivity)
    fun inject(lineNoteFragment: LineNoteFragment)

    fun inject(notePageActivity: NotePageActivity)
    fun inject(notePageFragment: NotePageFragment)

}