package com.example.rateflix.modules.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.rateflix.R
import com.example.rateflix.data.tvShow.TvShow
import com.squareup.picasso.Picasso

class TvShowSearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvShowImageView: ImageView?
    private val tvShowTitleView: TextView?

    init {
        tvShowImageView = itemView.findViewById(R.id.tvShowResultImage)
        tvShowTitleView = itemView.findViewById(R.id.tvShowResultTitle)
    }

    fun bind(tvShow: TvShow?) {
        if (tvShow == null) {
            return
        }
        itemView.setOnClickListener {
            val action = SearchDirections.actionSearchToTvShowFragment(tvShow)
            Navigation.findNavController(itemView).navigate(action)
        }

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500${tvShow.posterPath}")
            .into(tvShowImageView)
        tvShowTitleView?.text = tvShow.title
    }
}