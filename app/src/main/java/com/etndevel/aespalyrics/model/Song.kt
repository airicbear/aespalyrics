package com.etndevel.aespalyrics.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Song(
    @Json(name = "title") val title: String,
    @Json(name = "lyrics") val lyrics: Map<String, List<String>>
): Parcelable