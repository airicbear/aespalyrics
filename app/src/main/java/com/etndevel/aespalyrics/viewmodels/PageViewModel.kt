package com.etndevel.aespalyrics.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _language = MutableLiveData<List<String>>()
    val text: LiveData<String> = Transformations.map(_language) {
        it.toString()
    }

    fun setLanguage(lyrics: List<String>) {
        _language.value = lyrics
    }
}