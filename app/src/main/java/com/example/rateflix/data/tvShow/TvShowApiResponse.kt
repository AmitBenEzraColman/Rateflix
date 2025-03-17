package com.example.rateflix.data.tvShow

data class TvShowApiResponse(
    val results: Array<TvShow>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TvShowApiResponse

        return results.contentEquals(other.results)
    }

    override fun hashCode(): Int {
        return results.contentHashCode()
    }
}