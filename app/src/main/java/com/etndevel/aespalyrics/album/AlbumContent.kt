package com.etndevel.aespalyrics.album

import com.etndevel.aespalyrics.R
import java.time.LocalDate

object AlbumContent {
    val ALBUMS: MutableList<Album> = ArrayList()
    private val ITEM_MAP: MutableMap<String, Album> = HashMap()

    init {
        addItem(Album("Black Mamba", LocalDate.of(2020, 11, 17), R.drawable.black_mamba, listOf()))
        addItem(Album("Next Level", LocalDate.of(2021, 5, 17), R.drawable.next_level, listOf()))
    }

    private fun addItem(item: Album) {
        ALBUMS.add(item)
        ITEM_MAP[item.title] = item
    }
}