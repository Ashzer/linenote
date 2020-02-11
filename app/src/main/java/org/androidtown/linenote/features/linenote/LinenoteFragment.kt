package org.androidtown.linenote.features.linenote

import org.androidtown.linenote.R
import org.androidtown.linenote.core.platform.BaseFragment


class LinenoteFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            LinenoteFragment()
    }

    override fun layoutId() = R.layout.linenote_fragment


}
