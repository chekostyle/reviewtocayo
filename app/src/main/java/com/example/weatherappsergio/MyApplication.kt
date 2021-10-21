package com.example.weatherappsergio

import android.app.Application
import com.example.weatherappsergio.viewmodels.DisplayForecastDetailsViewModel
import com.example.weatherappsergio.viewmodels.DisplayForecastViewModel
import com.example.weatherappsergio.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(
                level = Level.INFO
            )
            androidContext(this@MyApplication)
            modules(viewModelModule)
        }
    }
}

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { DisplayForecastViewModel() }
    viewModel { DisplayForecastDetailsViewModel() }
}