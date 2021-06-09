package com.etndevel.aespalyrics.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Album(
    val title: String?,
    val releaseDate: LocalDate,
    val imageId: Int,
    val lyricsPaths: ArrayList<String>?
): Parcelable