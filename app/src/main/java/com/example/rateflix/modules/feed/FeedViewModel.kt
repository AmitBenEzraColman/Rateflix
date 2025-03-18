package com.example.rateflix.modules.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rateflix.data.review.Review
import com.example.rateflix.data.review.ReviewModel
import com.example.rateflix.data.user.User
import com.example.rateflix.data.user.UserModel

class FeedViewModel : ViewModel() {
    val reviews: LiveData<MutableList<Review>> = ReviewModel.instance.getAllReviews()
    val users: LiveData<MutableList<User>> = UserModel.instance.getAllUsers()
    val reviewsListLoadingState: MutableLiveData<ReviewModel.LoadingState> =
        ReviewModel.instance.reviewsListLoadingState

    fun reloadData() {
        UserModel.instance.refreshAllUsers()
        ReviewModel.instance.refreshAllReviews()
    }
}