package org.androidtown.linenote.features.linenote

import android.os.Parcel
import android.os.Parcelable
import org.androidtown.linenote.core.extension.empty

data class LineNoteImageView(val id: Int, val noteId: Int, val image: String) : Parcelable {
    companion object CREATOR : Parcelable.Creator<LineNoteImageView> {
        override fun createFromParcel(source: Parcel): LineNoteImageView {
            return LineNoteImageView(source)
        }

        override fun newArray(size: Int): Array<LineNoteImageView?> {
            return arrayOfNulls(size)
        }

        fun empty() = LineNoteImageView(-1, -1, String.empty())
    }

    override fun describeContents() = 0

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeInt(noteId)
            writeString(image)
        }
    }
}