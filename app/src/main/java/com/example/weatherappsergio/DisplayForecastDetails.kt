package com.example.weatherappsergio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherappsergio.databinding.ActivityDisplayForecastDetailsBinding
import com.example.weatherappsergio.viewmodels.DisplayForecastDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DisplayForecastDetails() : AppCompatActivity() {

    private val viewModel: DisplayForecastDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDisplayForecastDetailsBinding>(this, R.layout.activity_display_forecast_details)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.view = this
        }
    }