package com.skylightdevelopers.android.moviemaasala.app.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skylightdevelopers.android.moviemaasala.BuildConfig
import com.skylightdevelopers.android.moviemaasala.R
import com.skylightdevelopers.android.moviemaasala.app.NewReleasedMovieAdapter
import com.skylightdevelopers.android.moviemaasala.app.util.MConstants
import com.skylightdevelopers.android.moviemaasala.app.util.showLongSnackBar
import com.skylightdevelopers.android.moviemaasala.app.viewModel.TopRatedMovieViewModel
import com.skylightdevelopers.android.moviemaasala.core.ui.ViewState
import com.skylightdevelopers.android.moviemaasala.core.ui.base.BaseFragment
import com.skylightdevelopers.android.moviemaasala.core.utils.getViewModel
import com.skylightdevelopers.android.moviemaasala.core.utils.invisible
import com.skylightdevelopers.android.moviemaasala.core.utils.observeNotNull
import com.skylightdevelopers.android.moviemaasala.core.utils.visible
import kotlinx.android.synthetic.main.top_rated_movie_fragment_layout.*

class TopRatedMovieFragment : BaseFragment() {

    private val TAG = "TopRatedMovieFragment"
    private val newReleasedViewModel by lazy { getViewModel<TopRatedMovieViewModel>() }

    private val newReleasedAdapter = NewReleasedMovieAdapter()
    private var pageNum = 1
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private var mTotalPageCount = 0
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_rated_movie_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        linearLayoutManager = LinearLayoutManager(context)
        topRatedMovieRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = newReleasedAdapter
        }

        newReleasedViewModel.getTopRatedMoviesWithoutCache(
            BuildConfig.THE_MOVIE_API_KEY,
            MConstants.DEFAULT_LANGAUGE_CODE,
            pageNum
        )
        newReleasedViewModel._nowPlayingMovies.observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {
                    Log.d(
                        TAG,
                        "success from network : total pages :${state.data.totalPages} and this page num : ${state.data.page}"
                    )

                    state.data?.let {
                        (newReleasedAdapter.setData(it.results))
                        pageNum = it.page
                    }
                    topRatedMovieRecyclerView.visible()
                    mTotalPageCount = state.data.totalPages

                    if (pageNum <= mTotalPageCount)
                        pageNum++
                }
                is ViewState.SuccessFromDB -> Log.d(
                    TAG,
                    "success from db : " + state.data.toString()
                )
                is ViewState.Loading -> {
                    Log.d(TAG, "Loading : ${state.data}")
                    if (state.data) progressBar.visible() else progressBar.invisible()
                    isLoading = state.data
                }
                is ViewState.Error -> {
                    Log.e(TAG, state.message)
                    showLongSnackBar(topRatedMovieRecyclerView, state.message)
                }
            }
        }

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val visibleItemCount: Int = linearLayoutManager.getChildCount()
                val totalItemCount: Int = linearLayoutManager.getItemCount()
                val firstVisibleItemPosition: Int =
                    linearLayoutManager.findFirstVisibleItemPosition()

                if (!isLoading) {
                    Log.d(  TAG,"if pageNum : $pageNum and  mTotalPageCount : $mTotalPageCount")

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && pageNum <= mTotalPageCount) {
                        Log.d(  TAG,"if  ")

                        newReleasedViewModel.getTopRatedMoviesWithoutCache(
                            BuildConfig.THE_MOVIE_API_KEY,
                            MConstants.DEFAULT_LANGAUGE_CODE,
                            pageNum
                        )
                    }else{
                        Log.d(  TAG,"else ")
                    }
                }
            }
        }
        topRatedMovieRecyclerView.addOnScrollListener(scrollListener)
    }
}
