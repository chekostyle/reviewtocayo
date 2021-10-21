package com.example.weatherappsergio.viewmodels

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var _city = MutableLiveData<String>()
        val city : LiveData<String> = _city

    fun setCity(s: Editable) { _city.value = s.toString() }
}