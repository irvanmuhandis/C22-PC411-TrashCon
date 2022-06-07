package com.example.cpstone.ui.blank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlankViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is blankFragment"
    }
    val text: LiveData<String> = _text
}