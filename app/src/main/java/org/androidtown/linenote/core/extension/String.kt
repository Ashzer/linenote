package org.androidtown.linenote.core.extension

import android.text.Editable

fun String.Companion.empty() = ""
fun String.Companion.editText(str: String) = Editable.Factory.getInstance().newEditable(str)