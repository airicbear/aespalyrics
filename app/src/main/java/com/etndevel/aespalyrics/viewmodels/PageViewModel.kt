package com.etndevel.aespalyrics.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _language = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_language) {
        "Hello world from section: $it"
    }

    fun setLanguage(index: Int) {
        _language.value = index
    }
}