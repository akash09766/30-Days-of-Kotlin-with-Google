package com.skylightdevelopers.android.moviemaasala.app.dataSource

import android.util.Log
import com.google.gson.Gson
import com.skylightdevelopers.android.moviemaasala.app.api.GoogleService
import com.skylightdevelopers.android.moviemaasala.app.api.MoviesService
import com.skylightdevelopers.android.moviemaasala.app.model.NowPlaying
import com.skylightdevelopers.android.moviemaasala.app.util.MConstants
import com.skylightdevelopers.android.moviemaasala.app.util.NetworkError
import com.skylightdevelopers.android.moviemaasala.app.util.NetworkErrorForGoogle
import com.skylightdevelopers.android.moviemaasala.core.ui.ViewState
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.lang.ClassCastException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 */
interface MoviesRepository {

    /**
     * Retrieves all now playing movies
     */
    fun getNowPlayingMovies(
        apiKey: String,
        langCode: String,
        pageNum: Int
    ): Flow<ViewState<NowPlaying>>

    /**
     * Retrieves all top  rated movies
     */
    fun getTopRatedMovies(
        apiKey: String,
        langCode: String,
        pageNum: Int
    ): Flow<ViewState<NowPlaying>>

    /**
     * Retrieves all Upcoming movies
     */
    fun getUpcomingMovies(
        apiKey: String,
        langCode: String,
        pageNum: Int
    ): Flow<ViewState<NowPlaying>>

    fun getGooglePing(): Flow<String>
}

@Singleton
class DefaultMoviesRepository @Inject constructor(
    private val moviesService: MoviesService,
    private val googleService: GoogleService
) : MoviesRepository {
    val TAG = "DefaultNewsRepository"

    override fun getNowPlayingMovies(
        apiKey: String,
        langCode: String,
        pageNum: Int
    ): Flow<ViewState<NowPlaying>> {
        return flow {
            Log.d(TAG, "getNowPlayingMovies()")
            // 1. Start with loading + data from database
            emit(ViewState.loading(true))
            getGooglePing().collect { response ->

                if (response.contentEquals(MConstants.SUCCESS)) {
                    val movieSource =
                        moviesService.getNowPlayingMoviesCall(apiKey, langCode, pageNum)
                    emit(ViewState.loading(false))
                    emit(ViewState.success(movieSource))
                } else {
                    emit(ViewState.loading(false))
                    emit(ViewState.error(response))
                }
            }
        }.catch { error ->
            emit(ViewState.loading(false))
            val serverErrorResponse = getServerErrorFromException(error)
            if (serverErrorResponse == null) emit(ViewState.error(NetworkError(error).getAppErrorMessage())) else emit(
                ViewState.error(serverErrorResponse.statusMessage)
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getTopRatedMovies(
        apiKey: String,
        langCode: String,
        pageNum: Int
    ): Flow<ViewState<NowPlaying>> {
        return flow {
            Log.d(TAG, "getTopRatedMovies()")
            // 1. Start with loading + data from database
            emit(ViewState.loading(true))


            getGooglePing().collect { response ->

                if (response.contentEquals(MConstants.SUCCESS)) {
                    val movieSource =
                        moviesService.getTopRatedMoviesCall(apiKey, langCode, pageNum)
                    emit(ViewState.loading(false))
                    emit(ViewState.success(movieSource))
                } else {
                    emit(ViewState.loading(false))
                    emit(ViewState.error(response))
                }
            }
        }.catch { error ->
            emit(ViewState.loading(false))
            val serverErrorResponse = getServerErrorFromException(error)
            if (serverErrorResponse == null) emit(ViewState.error(NetworkError(error).getAppErrorMessage())) else emit(
                ViewState.error(serverErrorResponse.statusMessage)
            )
        }.flowOn(Dispatchers.IO)
    }
    override fun getUpcomingMovies(
        apiKey: String,
        langCode: String,
        pageNum: Int
    ): Flow<ViewState<NowPlaying>> {
        return flow {
            Log.d(TAG, "getUpcomingMovies()")
            // 1. Start with loading + data from database
            emit(ViewState.loading(true))


            getGooglePing().collect { response ->

                if (response.contentEquals(MConstants.SUCCESS)) {
                    val movieSource =
                        moviesService.getUpcomingMoviesCall(apiKey, langCode, pageNum)
                    emit(ViewState.loading(false))
                    emit(ViewState.success(movieSource))
                } else {
                    emit(ViewState.loading(false))
                    emit(ViewState.error(response))
                }
            }
        }.catch { error ->
            emit(ViewState.loading(false))
            val serverErrorResponse = getServerErrorFromException(error)
            if (serverErrorResponse == null) emit(ViewState.error(NetworkError(error).getAppErrorMessage())) else emit(
                ViewState.error(serverErrorResponse.statusMessage)
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getGooglePing(): Flow<String> = flow {
        val response = googleService.getGoogle()
        if (response.isSuccessful)
            emit(MConstants.SUCCESS)
    }.catch {
        emit(NetworkErrorForGoogle(it).getAppErrorMessage())
    }.flowOn(Dispatchers.IO)

    private fun getServerErrorFromException(throwable: Throwable): NowPlaying? {
        return try {
            val error = (throwable as HttpException).response()?.errorBody()?.string().toString()
            Gson().fromJson(error, NowPlaying::class.java)
        } catch (e: ClassCastException) {
            Log.e(TAG,"getServerErrorFromException() ClassCastException %%%%%%%%%%%%%%%%")
            null
        }
    }
}

@Module
interface MoviesRepositoryModule {
    /* Exposes the concrete implementation for the interface */
    @Binds
    fun it(it: DefaultMoviesRepository): MoviesRepository
}