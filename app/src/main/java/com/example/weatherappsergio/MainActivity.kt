package com.example.weatherappsergio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherappsergio.constants.SELECTED_CITY
import com.example.weatherappsergio.databinding.ActivityMainBinding
import com.example.weatherappsergio.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.view = this
    }

    fun navigateToForecast(city: String?) {

        // Avoid calling the activity if user input is empty
        if (!city.isNullOrEmpty()) {
            val intent = Intent(this, DisplayForecast::class.java).apply {
                putExtra(SELECTED_CITY, city)
            }
            startActivity(intent)
        }
    }
}