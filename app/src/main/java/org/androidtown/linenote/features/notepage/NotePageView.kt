package org.androidtown.linenote.features.notepage

import android.os.Parcel
import android.os.Parcelable
import org.androidtown.linenote.core.extension.empty

data class NotePageView(val id: Int, val image: String, val title: String, val content: String) :
    Parcelable {
    companion object CREATOR : Parcelable.Creator<NotePageView> {
        override fun createFromParcel(source: Parcel): NotePageView {
            return NotePageView(source)
        }

        override fun newArray(size: Int): Array<NotePageView?> {
            return arrayOfNulls(size)
        }

        fun empty() = NotePageView(-1, String.empty(), String.empty(), String.empty())
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
            writeString(image)
            writeString(title)
            writeString(content)
        }
    }
}