package com.example.weatherappsergio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappsergio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val CITY = "CITY"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lookupCityButton.setOnClickListener { lookupCity() }
        binding.editTextCityName.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun lookupCity() {
        val city = binding.editTextCityName.text.toString()
        if (city == "") {
            return
        }
        val intent = Intent(this, DisplayForecast::class.java).apply {
            putExtra(CITY, city)
        }
        startActivity(intent)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}