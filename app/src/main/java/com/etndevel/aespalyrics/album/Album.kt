package com.etndevel.aespalyrics.album

import java.time.LocalDate

data class Album(
    val title: String,
    val releaseDate: LocalDate,
    val imageId: Int,
    val lyricsPaths: List<String>
)