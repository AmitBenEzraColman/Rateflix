package com.example.rateflix.data.tvShow

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TvShow(
    val id: Int,
    @SerializedName("original_name")
    val title: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
) : Serializable