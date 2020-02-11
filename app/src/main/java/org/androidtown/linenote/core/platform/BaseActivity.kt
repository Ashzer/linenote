package org.androidtown.linenote.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.androidtown.linenote.AndroidApplication
import org.androidtown.linenote.core.di.ApplicationComponent
import org.androidtown.linenote.core.extension.inTransaction

abstract class BaseActivity : AppCompatActivity(){
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }
    abstract var layout: Int
    abstract var fragmentId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {

        (supportFragmentManager.findFragmentById(
            fragmentId
        ) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                fragmentId, fragment()
            )
        }


    abstract fun fragment(): BaseFragment

}