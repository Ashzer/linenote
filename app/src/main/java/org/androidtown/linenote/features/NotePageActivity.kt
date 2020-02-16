package org.androidtown.linenote.features

import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.androidtown.linenote.R
import org.androidtown.linenote.core.navigation.Navigator
import org.androidtown.linenote.core.platform.BaseActivity
import org.androidtown.linenote.features.notepage.NotePageFragment
import javax.inject.Inject

class NotePageActivity : BaseActivity() {
    companion object {
        fun callingIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, NotePageActivity::class.java)
            intent.putExtra("id", id)
            return intent
        }
    }
    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override var layout = R.layout.notepage_activity
    override var fragmentId = R.id.notePage_flo_container
    override fun fragment() =
        NotePageFragment(intent)

}
