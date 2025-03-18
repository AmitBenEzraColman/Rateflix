package com.example.rateflix.modules.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rateflix.data.tvShow.TvShow
import com.example.rateflix.data.tvShow.TvShowServiceClient

class SearchViewModel : ViewModel() {
    var tvShows: MutableLiveData<MutableList<TvShow>> = MutableLiveData()

    fun clearTvShows() {
        tvShows.postValue(mutableListOf())
    }

    fun refreshTvShows(query: String) {
        TvShowServiceClient.instance.searchMovies(query) {
            tvShows.postValue(it)
        }
    }



}