package com.skylightdevelopers.android.moviemaasala.app.di

import androidx.lifecycle.ViewModel
import com.skylightdevelopers.android.moviemaasala.app.viewModel.NewReleasedViewModel
import com.skylightdevelopers.android.moviemaasala.core.di.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface NewReleasedViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewReleasedViewModel::class)
    fun bindNewReleasedMovieViewModel(newsArticleViewModel: NewReleasedViewModel): ViewModel
}