package com.example.weatherappsergio

import android.app.Application
import com.example.weatherappsergio.constants.APP_ID
import com.example.weatherappsergio.services.implementations.WeatherServiceImpl
import com.example.weatherappsergio.services.interfaces.IWeatherService
import com.example.weatherappsergio.services.interfaces.retrofit.IOpenWeatherMapApiService
import com.example.weatherappsergio.viewmodels.DisplayForecastDetailsViewModel
import com.example.weatherappsergio.viewmodels.DisplayForecastViewModel
import com.example.weatherappsergio.viewmodels.MainViewModel
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(
                level = Level.INFO
            )
            androidContext(this@MyApplication)
            modules(networkModule, serviceModule, viewModelModule)
        }
    }
}

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { DisplayForecastViewModel(get()) }
    viewModel { DisplayForecastDetailsViewModel(get()) }
}

val networkModule = module {

    single { getOkHttpClient() }

    single { Gson() }

    single { getRetrofitClient(get()) }

    single { get<Retrofit>().create(IOpenWeatherMapApiService::class.java) }

}

val serviceModule = module {
    single<IWeatherService> { WeatherServiceImpl(get()) }
}

private fun getRetrofitClient(okHttpClient : OkHttpClient) = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl("https://api.openweathermap.org/data/2.5/")
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun getOkHttpClient(): OkHttpClient {
    val requestInterceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("appid", APP_ID)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return@Interceptor chain.proceed(request)
    }

    val logging =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    return OkHttpClient().newBuilder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(logging)
        .build()
}