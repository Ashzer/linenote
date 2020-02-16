package org.androidtown.linenote.features.notepage

import android.os.Parcel
import android.os.Parcelable
import org.androidtown.linenote.core.extension.empty

data class NotePageImageView(val id: Int, val noteId: Int, val image: String) : Parcelable {
    companion object CREATOR : Parcelable.Creator<NotePageImageView> {
        override fun createFromParcel(source: Parcel): NotePageImageView {
            return NotePageImageView(source)
        }

        override fun newArray(size: Int): Array<NotePageImageView?> {
            return arrayOfNulls(size)
        }

        fun empty() = NotePageImageView(-1, -1, String.empty())
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