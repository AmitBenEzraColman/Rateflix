package com.example.rateflix.modules.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rateflix.R
import com.example.rateflix.data.tvShow.TvShow

class SearchRecycleAdapter(var tvShows: MutableList<TvShow>?) :
    RecyclerView.Adapter<TvShowSearchResultViewHolder>() {

    override fun getItemCount(): Int {
        return tvShows?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowSearchResultViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tv_show_search_result, parent, false)
        return TvShowSearchResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TvShowSearchResultViewHolder, position: Int) {
        val tvShow = tvShows?.get(position)
        holder.bind(tvShow)
    }
}