package org.androidtown.linenote.core.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun View.visible() { this.visibility = View.VISIBLE }

fun View.invisible() { this.visibility = View.GONE }

fun View.focusble() {
    this.setFocusable(true)
    this.setFocusableInTouchMode(true)
}

fun View.unfocusble() {
    this.setFocusable(false)
    this.setFocusableInTouchMode(false)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)