package org.androidtown.linenote.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import org.androidtown.linenote.AndroidApplication
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

}