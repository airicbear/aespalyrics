package com.etndevel.aespalyrics.activities

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.etndevel.aespalyrics.adapters.SongPagerAdapter
import com.etndevel.aespalyrics.databinding.ActivitySongBinding
import com.etndevel.aespalyrics.model.Song

class SongActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val song = intent.getParcelableExtra<Song>("song") ?: Song("", HashMap())

        binding = ActivitySongBinding.inflate(layoutInflater)
        binding.title.text = song.title

        setContentView(binding.root)

        val sectionsPagerAdapter = SongPagerAdapter(
            supportFragmentManager,
            song
        )
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}