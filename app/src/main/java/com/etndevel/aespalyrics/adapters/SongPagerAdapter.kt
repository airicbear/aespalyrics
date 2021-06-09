package com.etndevel.aespalyrics.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.etndevel.aespalyrics.fragments.SongFragment
import com.etndevel.aespalyrics.model.Song

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SongPagerAdapter(fm: FragmentManager, private val song: Song) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return SongFragment.newInstance(song.lyrics.values.toList()[position].toTypedArray())
    }

    override fun getPageTitle(position: Int): CharSequence {
        return song.lyrics.keys.toList()[position]
    }

    override fun getCount(): Int {
        return song.lyrics.entries.size
    }
}