package com.skylightdevelopers.android.moviemaasala.app.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skylightdevelopers.android.moviemaasala.app.dataSource.MoviesRepository
import com.skylightdevelopers.android.moviemaasala.app.model.NowPlaying
import com.skylightdevelopers.android.moviemaasala.core.ui.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopRatedMovieViewModel @Inject constructor(
    val moviesRepository: MoviesRepository
) : ViewModel() {

    val TAG = "TopRatedMovieViewModel"

    private var nowPlayingMovies = MutableLiveData<ViewState<NowPlaying>>()

    private var item = NowPlaying("", 0, false, 0, 0, arrayListOf())

    val _nowPlayingMovies: LiveData<ViewState<NowPlaying>>
        get() = nowPlayingMovies

    lateinit var job: Job
    private var currentPageNumber = 0

    fun getTopRatedMoviesWithoutCache(apiKey: String, langCode: String, pageNum: Int) {

        Log.d(TAG, "Requested page Num : ${pageNum} and model page Num : ${item.page}")

        if (pageNum <= currentPageNumber) {
            Log.d(TAG, "caching data......")
            return
        }

        Log.d(TAG, "Fetching fresh data......")

        job = CoroutineScope(Dispatchers.Main).launch {
            moviesRepository.getTopRatedMovies(apiKey, langCode, pageNum)
                .collect { data ->
                    currentPageNumber = pageNum

                    when (data) {
                        is ViewState.Success -> {
                            item.page = data.data.page
                            item.totalPages = data.data.totalPages
                            data.data.results?.let { item.results?.addAll(it) }
                            nowPlayingMovies.value = ViewState.success(item)
                        }
                        is ViewState.Loading -> {
                            val loadingData = data.data
                            nowPlayingMovies.value = ViewState.loading(loadingData)
                        }
                        is ViewState.Error -> {
                            val errorMsg = data.message
                            nowPlayingMovies.value = ViewState.error(errorMsg)
                        }
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}
