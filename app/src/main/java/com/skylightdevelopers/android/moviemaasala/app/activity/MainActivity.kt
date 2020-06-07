package com.skylightdevelopers.android.moviemaasala.app.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.skylightdevelopers.android.moviemaasala.R
import com.skylightdevelopers.android.moviemaasala.core.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val TAG = "NewsActivity"

    private lateinit var navController: NavController
//    private val newReleasedViewModel by lazy { getViewModel<NewReleasedViewModel>() }

    /**
     * Starting point of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottom_nav.setupWithNavController(navController)

//        setObserver()
    }

    /*private fun setObserver() {
        Log.d(TAG, "setObserver() :")
        newReleasedViewModel.getNowPlayingMovies("asdaskd"*//*BuildConfig.THE_MOVIE_API_KEY*//*,MConstants.DEFAULT_LANGAUGE_CODE,1)
        newReleasedViewModel._nowPlayingMovies.observeNotNull(this) { state ->
            if (state is ViewState.Success) {
                Log.d(
                    TAG,
                    "success from network : total pages :${state.data.totalPages} and this page num : ${state.data.page}"
                )
            } else if (state is ViewState.SuccessFromDB) Log.d(
                TAG,
                "success from db : " + state.data.toString()
            )
            else if (state is ViewState.Loading) {
                Log.d(TAG, "Loading : ${state.data}")
            } else if (state is ViewState.Error) {
                Log.e(TAG, state.message)
            }
        }
    }*/

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
