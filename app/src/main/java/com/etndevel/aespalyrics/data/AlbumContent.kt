package com.etndevel.aespalyrics.data

import com.etndevel.aespalyrics.R
import com.etndevel.aespalyrics.model.Album
import java.time.LocalDate

object AlbumContent {
    val ALBUMS: MutableList<Album> = ArrayList()

    init {
        addItem(Album("Next Level", LocalDate.of(2021, 5, 17), R.drawable.next_level, arrayListOf("lyrics/next_level/next_level.json")))
        addItem(Album("Black Mamba", LocalDate.of(2020, 11, 17), R.drawable.black_mamba, arrayListOf("lyrics/black_mamba/black_mamba.json")))
    }

    private fun addItem(item: Album) {
        ALBUMS.add(item)
    }
}