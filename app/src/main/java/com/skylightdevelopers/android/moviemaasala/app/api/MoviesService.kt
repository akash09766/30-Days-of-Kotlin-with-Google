package com.skylightdevelopers.android.moviemaasala.app.api

import com.skylightdevelopers.android.moviemaasala.app.model.NowPlaying
import com.skylightdevelopers.android.moviemaasala.app.util.MConstants
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Fetch all the movie data from themoviedb API.
 */
interface MoviesService {

    /**
     * Retrieves all now playing movies from themoviedb API.
     */
    @GET(MConstants.NEW_RELEASED)
    suspend fun getNowPlayingMoviesCall(
        @Query(MConstants.API_KEY_NAME) apiKey: String,
        @Query(MConstants.LANGUAGE_KEY_NAME) langCode: String,
        @Query(MConstants.PAGE_KEY_NAME) page: Int

    ): NowPlaying

    /**
     * Retrieves all upcoming playing movies from themoviedb API.
     */
    @GET(MConstants.UPCOMING_MOVIES)
    suspend fun getUpcomingMoviesCall(
        @Query(MConstants.API_KEY_NAME) apiKey: String,
        @Query(MConstants.LANGUAGE_KEY_NAME) langCode: String,
        @Query(MConstants.PAGE_KEY_NAME) page: Int

    ): NowPlaying

    /**
     * Retrieves all upcoming playing movies from themoviedb API.
     */
    @GET(MConstants.TOP_RATED_MOVIES)
    suspend fun getTopRatedMoviesCall(
        @Query(MConstants.API_KEY_NAME) apiKey: String,
        @Query(MConstants.LANGUAGE_KEY_NAME) langCode: String,
        @Query(MConstants.PAGE_KEY_NAME) page: Int

    ): NowPlaying

}
