package org.androidtown.linenote.core.navigation

import android.content.Context
import org.androidtown.linenote.features.LineNoteActivity
import org.androidtown.linenote.features.NotePageActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor() {
    fun showMain(context: Context) = context.startActivity(LineNoteActivity.callingIntent(context))
    fun showNote(context: Context, id: Int) = context.startActivity(NotePageActivity.callingIntent(context, id))

}