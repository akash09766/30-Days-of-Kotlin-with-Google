package com.skylightdevelopers.android.moviemaasala.core.di

import android.app.Application
import com.skylightdevelopers.android.moviemaasala.app.api.MoviesService
import com.skylightdevelopers.android.moviemaasala.app.util.MConstants
import com.skylightdevelopers.android.moviemaasala.app.util.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object MoviesServiceModule {

    @Singleton
    @Provides
    fun provideMoviesService(application: Application): MoviesService {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val oktHttpClient = OkHttpClient.Builder()
            .connectTimeout(MConstants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(MConstants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(MConstants.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(NetworkConnectionInterceptor(application.applicationContext))
            .addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(MConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(oktHttpClient.build())
            .build()
            .create(MoviesService::class.java)
    }
}