package com.etndevel.aespalyrics.model

import android.os.Parcel
import android.os.Parcelable
import com.beust.klaxon.Json

data class Song(
    @Json(name = "title") val title: String,
    @Json(name = "lyrics") val lyrics: HashMap<String, List<String>>?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readHashMap(ClassLoader.getSystemClassLoader()) as HashMap<String, List<String>>?
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }
}
