package com.example.rateflix.data.tvShow

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TvShowService {
    @GET("search/tv")
    fun searchMovies(
        @Query("query") query: String,
        @Header("Authorization") authorization: String
    ): Call<TvShowApiResponse>
}
