package com.skylightdevelopers.android.moviemaasala.app.util

object MConstants {

    const val SUCCESS = "SUCCESS"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val G_BASE_URL = "https://google.com"

    const val POLULAR_MOVIES = "movie/popular"
    const val NEW_RELEASED = "movie/now_playing"
    const val UPCOMING_MOVIES = "movie/upcoming"
    const val TOP_RATED_MOVIES = "movie/top_rated"


    const val IMAGE_URL_BASE_URL_TYPE_ORIGINAL = "https://image.tmdb.org/t/p/w185"
    const val IMAGE_URL_BASE_URL_TYPE_HEADER = "https://image.tmdb.org/t/p/w780"

    const val DEFAULT_LANGAUGE_CODE = "en-US"

    const val PAGE_KEY_NAME = "page"
    const val LANGUAGE_KEY_NAME = "language"
    const val API_KEY_NAME = "api_key"
    const val SNACK_BAR_OKAY_BTN_TEXT = "Okay"


    const val NOT_CONNECTED_TO_INTERNET = "You are not connected to internet."
    const val NO_INTERNET =
        "The internet connection you are connected to is not working please check."

    const val TRY_AGAIN = "Try Again!"

    const val SERVER_NOT_RESPONDING =
        "Our servers are not responding or taking long time to respond. Our best minds are on it. You may retry or check back soon!"

    const val INVALID_SERVER_RESPONSE =
        "Somethings wrong with our servers at this movement.Our best minds are on it.\n Please try after some time."

    const val CONNECTION_TIME_OUT = 10L

}