package com.etndevel.aespalyrics.album

import com.etndevel.aespalyrics.R
import java.time.LocalDate

object AlbumContent {
    val ALBUMS: MutableList<Album> = ArrayList()

    init {
        addItem(Album("Next Level", LocalDate.of(2021, 5, 17), R.drawable.next_level, arrayListOf<String>("Next Level")))
        addItem(Album("Black Mamba", LocalDate.of(2020, 11, 17), R.drawable.black_mamba, arrayListOf<String>("Black Mamba")))
    }

    private fun addItem(item: Album) {
        ALBUMS.add(item)
    }
}