package org.androidtown.linenote.features

import android.os.Bundle
import org.androidtown.linenote.R
import org.androidtown.linenote.core.platform.BaseActivity
import org.androidtown.linenote.features.linenote.LinenoteFragment

class LinenoteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linenote_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.linenote_flo_container, LinenoteFragment.newInstance())
                .commitNow()
        }
    }

    override var layout = R.layout.linenote_activity
    override var fragmentId = R.id.linenote_flo_container
    override fun fragment() =
        LinenoteFragment()
}
