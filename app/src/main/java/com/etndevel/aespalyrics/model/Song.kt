package com.etndevel.aespalyrics.model

import android.os.Parcel
import android.os.Parcelable
import com.beust.klaxon.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    @Json(name = "title") val title: String,
    @Json(name = "lyrics") val lyrics: HashMap<String, List<String>>
): Parcelable