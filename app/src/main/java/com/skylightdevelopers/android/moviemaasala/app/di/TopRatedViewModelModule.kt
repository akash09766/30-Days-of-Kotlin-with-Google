package com.skylightdevelopers.android.moviemaasala.app.di

import androidx.lifecycle.ViewModel
import com.skylightdevelopers.android.moviemaasala.app.viewModel.TopRatedMovieViewModel
import com.skylightdevelopers.android.moviemaasala.core.di.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TopRatedViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TopRatedMovieViewModel::class)
    fun bindTopRatedMovieViewModel(newsArticleViewModel: TopRatedMovieViewModel): ViewModel
}