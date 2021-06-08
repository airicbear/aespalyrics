package com.etndevel.aespalyrics.album

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate
import java.util.ArrayList

data class Album(
    val title: String?,
    val releaseDate: LocalDate,
    val imageId: Int,
    val lyricsPaths: ArrayList<String>?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readSerializable() as LocalDate,
        parcel.readInt(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeSerializable(releaseDate)
        parcel.writeInt(imageId)
        parcel.writeStringList(lyricsPaths)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Album> {
        override fun createFromParcel(parcel: Parcel): Album {
            return Album(parcel)
        }

        override fun newArray(size: Int): Array<Album?> {
            return arrayOfNulls(size)
        }
    }
}