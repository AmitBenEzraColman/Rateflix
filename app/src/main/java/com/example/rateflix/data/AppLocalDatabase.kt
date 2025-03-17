package com.example.rateflix.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rateflix.RateflixApplication
import com.example.rateflix.data.review.Review
import com.example.rateflix.data.review.ReviewDAO
import com.example.rateflix.data.user.User
import com.example.rateflix.data.user.UserDAO


@Database(entities = [Review::class, User::class], version = 8, exportSchema = true)
abstract class AppLocalDbRepository : RoomDatabase() {
    abstract fun reviewDao(): ReviewDAO
    abstract fun userDao(): UserDAO
}

object AppLocalDatabase {
    val db: AppLocalDbRepository by lazy {
        val context = RateflixApplication.Globals.appContext
            ?: throw IllegalStateException("Application context not available")

        Room.databaseBuilder(
            context,
            AppLocalDbRepository::class.java,
            "rateflix"
        ).fallbackToDestructiveMigration()
            .build()
    }
}