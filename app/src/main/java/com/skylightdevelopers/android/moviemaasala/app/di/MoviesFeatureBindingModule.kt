package com.skylightdevelopers.android.moviemaasala.app.di

import com.skylightdevelopers.android.moviemaasala.app.activity.MainActivity
import com.skylightdevelopers.android.moviemaasala.app.dataSource.MoviesRepositoryModule
import com.skylightdevelopers.android.moviemaasala.app.fragment.NewReleasedFragment
import com.skylightdevelopers.android.moviemaasala.app.fragment.TopRatedMovieFragment
import com.skylightdevelopers.android.moviemaasala.app.fragment.UpcomingMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Describes list of activities which require
 * DI.
 *
 * Each [ContributesAndroidInjector] generates a sub-component
 * for each activity under the hood
 */
@Module(
    includes = [
        NewReleasedViewModelModule::class,
        UpComingViewModelModule::class,
        TopRatedViewModelModule::class,
        MoviesRepositoryModule::class
    ]
)
interface MoviesFeatureBindingModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun contributeNewReleasedFragment(): NewReleasedFragment

    @ContributesAndroidInjector
    fun contributeUpcomingMovieFragment(): UpcomingMovieFragment

    @ContributesAndroidInjector
    fun contributeTopRatedMovieFragment(): TopRatedMovieFragment
}