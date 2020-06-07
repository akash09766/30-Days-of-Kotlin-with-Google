package com.skylightdevelopers.android.moviemaasala.app.di

import android.app.Application
import com.skylightdevelopers.android.moviemaasala.app.MovieMaasalaApp
import com.skylightdevelopers.android.moviemaasala.core.di.GoogleServiceModule
import com.skylightdevelopers.android.moviemaasala.core.di.MoviesServiceModule
import com.skylightdevelopers.android.moviemaasala.core.di.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            // Dagger support
            AndroidInjectionModule::class,
            // Global
            MoviesServiceModule::class,
            GoogleServiceModule::class,
            ViewModelFactoryModule::class,

            // News feature
            MoviesFeatureBindingModule::class
        ]
)
interface AppComponent : AndroidInjector<MovieMaasalaApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    override fun inject(newsApp: MovieMaasalaApp)
}
