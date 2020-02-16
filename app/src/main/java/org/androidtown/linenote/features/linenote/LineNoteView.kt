package org.androidtown.linenote.features.linenote

import android.os.Parcel
import android.os.Parcelable
import org.androidtown.linenote.core.extension.empty

data class LineNoteView(val id: Int, val title: String, val content: String, val thumbnail:String) : Parcelable {

    companion object CREATOR : Parcelable.Creator<LineNoteView> {
        override fun createFromParcel(source: Parcel): LineNoteView {
            return LineNoteView(source)
        }

        override fun newArray(size: Int): Array<LineNoteView?> {
            return arrayOfNulls(size)
        }

        fun empty() = LineNoteView(-1, String.empty(), String.empty(),String.empty())
    }

    override fun describeContents() = 0

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(title)
            writeString(content)
            writeString(thumbnail)
        }
    }
}