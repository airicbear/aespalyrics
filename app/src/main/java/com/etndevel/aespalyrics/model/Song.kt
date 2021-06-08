package com.etndevel.aespalyrics.model

import com.beust.klaxon.Json

data class Song(
    @Json(name = "title") val title: String,
    @Json(name = "lyrics") val lyrics: List<String>
)
