package com.example.rateflix.modules.tvShow

import androidx.lifecycle.ViewModel
import com.example.rateflix.data.tvShow.TvShow

class TvShowViewModel : ViewModel() {
    var tvShowDetailsData: TvShow? = null

    fun setTvShowDetails(tvShow: TvShow) {
        tvShowDetailsData = tvShow
    }
}