package com.etndevel.aespalyrics.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class LyricsViewModel : ViewModel() {

    private val _lyrics = MutableLiveData<List<String>>()
    val text: LiveData<String> = Transformations.map(_lyrics) {
        it.toString()
    }

    fun setLanguage(lyrics: List<String>) {
        _lyrics.value = lyrics
    }
}