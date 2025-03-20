package com.example.rateflix.data.tvShow

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

class TvShowServiceClient private constructor() {

    companion object {
        val instance: TvShowServiceClient = TvShowServiceClient()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiKey =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNWFkM2Q2MDMxMjI5NWJkNzE2NTE5MGVlMTJkOGJkZiIsIm5iZiI6MTczODY2OTkwNS4xOTcsInN1YiI6IjY3YTFmZjUxNjA1ZmEwZDUwMDAzMTA1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.LkooD_7MLeCpOv7gpdhe03vv4IlF1BI-9Ga7SfI55CE"

    private val tvShowService = retrofit.create(TvShowService::class.java)

    fun searchMovies(
        query: String,
        callback: (MutableList<TvShow>) -> Unit
    ) {

        val encodedSearch = URLEncoder.encode(query, "UTF-8")
        val searchMovies = tvShowService.searchMovies(encodedSearch, "Bearer $apiKey")

        searchMovies.enqueue(object : retrofit2.Callback<TvShowApiResponse> {
            override fun onResponse(
                call: retrofit2.Call<TvShowApiResponse>,
                response: retrofit2.Response<TvShowApiResponse>
            ) {
                if (response.isSuccessful) {
                    val moviesList = response.body()?.results
                        ?.filter { movie -> movie.popularity > 1 }
                        ?.sortedByDescending { movie -> movie.popularity }
                    callback(moviesList as? MutableList<TvShow> ?: mutableListOf())
                } else {
                    throw Exception("Failed to fetch movies")
                }
            }

            override fun onFailure(call: retrofit2.Call<TvShowApiResponse>, t: Throwable) {
                throw t
            }
        })
    }
}