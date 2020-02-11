package org.androidtown.linenote

import android.app.Application
import org.androidtown.linenote.core.di.ApplicationComponent
import org.androidtown.linenote.core.di.ApplicationModule
import org.androidtown.linenote.core.di.DaggerApplicationComponent

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}