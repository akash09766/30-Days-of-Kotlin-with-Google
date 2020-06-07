package com.skylightdevelopers.android.moviemaasala.app.model


import com.google.gson.annotations.SerializedName

data class ResultsItem(@SerializedName("overview")
                       val overview: String = "",
                       @SerializedName("original_language")
                       val originalLanguage: String = "",
                       @SerializedName("original_title")
                       val originalTitle: String = "",
                       @SerializedName("video")
                       val video: Boolean = false,
                       @SerializedName("title")
                       val title: String = "",
                       @SerializedName("genre_ids")
                       val genreIds: List<Integer>?,
                       @SerializedName("poster_path")
                       val posterPath: String = "",
                       @SerializedName("backdrop_path")
                       val backdropPath: String = "",
                       @SerializedName("release_date")
                       val releaseDate: String = "",
                       @SerializedName("popularity")
                       val popularity: Double = 0.0,
                       @SerializedName("vote_average")
                       val voteAverage: Double = 0.0,
                       @SerializedName("id")
                       val id: Int = 0,
                       @SerializedName("adult")
                       val adult: Boolean = false,
                       @SerializedName("vote_count")
                       val voteCount: Int = 0)


data class Dates(@SerializedName("maximum")
                 val maximum: String = "",
                 @SerializedName("minimum")
                 val minimum: String = "")


data class NowPlaying(@SerializedName("status_message")
                      var statusMessage: String = "",
                      @SerializedName("status_code")
                      var statusCode: Int = 0,
                      @SerializedName("success")
                      var success: Boolean = false,
                      @SerializedName("page")
                      var page: Int = 0,
                      @SerializedName("total_pages")
                      var totalPages: Int = 0,
                      @SerializedName("results")
                      var results: ArrayList<ResultsItem>?,
                      @SerializedName("total_results")
                      var totalResults: Int = 0)


