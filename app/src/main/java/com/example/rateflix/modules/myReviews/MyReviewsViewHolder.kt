package com.example.rateflix.modules.myReviews

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rateflix.R
import com.example.rateflix.data.review.Review
import com.example.rateflix.data.user.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso

class MyReviewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val reviewImageView: ImageView?
    val profileImageView: ImageView?
    val profileName: TextView?
    val tvShowName: TextView?
    val reviewDescription: TextView?
    val reviewRating: TextView?
    val editButton: Button
    val deleteButton: Button


    init {
        reviewImageView = itemView.findViewById(R.id.CardImage)
        profileImageView = itemView.findViewById(R.id.ProfileImageView)
        profileName = itemView.findViewById(R.id.ProfileName)
        tvShowName = itemView.findViewById(R.id.TvShowName)
        reviewDescription = itemView.findViewById(R.id.ReviewDescription)
        reviewRating = itemView.findViewById(R.id.ReviewRating)
        editButton = itemView.findViewById(R.id.EditButton)
        deleteButton = itemView.findViewById(R.id.DeleteButton)
    }

    @SuppressLint("SetTextI18n")
    fun bind(
        review: Review?,
        reviewer: User?,
        editReviewClickListener: () -> Unit,
        deleteReviewClickListener: () -> Unit
    ) {
        Log.d("TAG", "review ${review?.score}")
        Picasso.get()
            .load(review?.reviewImage)
            .into(reviewImageView)
        Picasso.get()
            .load(reviewer?.profileImage)
            .into(profileImageView)
        val reviewerName = "${reviewer?.firstName ?: ""} ${reviewer?.lastName ?: ""}"
        profileName?.text = reviewerName
        tvShowName?.text = review?.tvShowName
        reviewDescription?.text = review?.description
        reviewRating?.text = "Rating: ${review?.score} ★"
        deleteButton.setOnClickListener {
            MaterialAlertDialogBuilder(itemView.context)
                .setTitle("Delete Review")
                .setMessage("Do you want to delete this Review?")
                .setNeutralButton("Cancel") { _, _ ->
                }
                .setPositiveButton("Delete") { _, _ ->
                    deleteReviewClickListener()
                }
                .show()
        }
        editButton.setOnClickListener {
            editReviewClickListener()
        }
    }
}