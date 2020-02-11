package org.androidtown.linenote.core.di


import dagger.Component
import org.androidtown.linenote.AndroidApplication
import org.androidtown.linenote.core.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
}