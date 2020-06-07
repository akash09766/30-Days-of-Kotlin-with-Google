package com.skylightdevelopers.android.moviemaasala.app.di

import androidx.lifecycle.ViewModel
import com.skylightdevelopers.android.moviemaasala.app.viewModel.UpcomingMovieViewModel
import com.skylightdevelopers.android.moviemaasala.core.di.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UpComingViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UpcomingMovieViewModel::class)
    fun bindUpcomingMovieViewModel(newsArticleViewModel: UpcomingMovieViewModel): ViewModel
}