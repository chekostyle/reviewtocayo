package com.example.weatherappsergio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.weatherappsergio.constants.MAIN
import com.example.weatherappsergio.constants.SELECTED_CITY
import com.example.weatherappsergio.dao.weatherapi.Main
import com.example.weatherappsergio.databinding.ActivityDisplayForecastDetailsBinding
import com.example.weatherappsergio.viewmodels.DisplayForecastDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DisplayForecastDetails() : AppCompatActivity() {

    private val viewModel: DisplayForecastDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val city = intent.getStringExtra(SELECTED_CITY).toString()
        viewModel.setCity(city)

        val binding = DataBindingUtil.setContentView<ActivityDisplayForecastDetailsBinding>(this, R.layout.activity_display_forecast_details)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.view = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.getForecastForSelectedCity()
    }
}