package com.skylightdevelopers.android.moviemaasala.core.di

import dagger.Component

@Component(modules = [AppModule::class])
interface ApiComponent {
    fun inject(apiService: MoviesServiceModule)
}