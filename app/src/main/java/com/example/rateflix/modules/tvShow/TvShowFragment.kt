package com.example.rateflix.modules.tvShow

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rateflix.R
import com.squareup.picasso.Picasso


class TvShowFragment : Fragment() {

    private val args by navArgs<TvShowFragmentArgs>()
    private lateinit var viewModel: TvShowViewModel
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_tv_show, container, false)

        viewModel = ViewModelProvider(this)[TvShowViewModel::class.java]
        viewModel.setTvShowDetails(args.selectedTvShow)
        loadTvShowDetails(root)
        root.findViewById<Button>(R.id.addReview).setOnClickListener {
            addAddReviewOnClickHandler()
        }

        return root
    }

    private fun addAddReviewOnClickHandler() {
        val action =
            TvShowFragmentDirections.actionTvShowFragmentToNewReview(viewModel.tvShowDetailsData!!)
        findNavController().navigate(action)
    }

    private fun loadTvShowDetails(root: View) {
        val tvShowTitle: TextView = root.findViewById(R.id.tvShowTitle)
        val tvShowPoster: ImageView = root.findViewById(R.id.tvShowPoster)
        val tvShowDescription: TextView = root.findViewById(R.id.tvShowDescription)

        viewModel.tvShowDetailsData?.let { tvShow ->
            tvShowTitle.text = tvShow.title

            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${tvShow.backdropPath}")
                .into(tvShowPoster)

            tvShowDescription.text = tvShow.overview
            tvShowDescription.movementMethod = ScrollingMovementMethod()
        }
    }
}