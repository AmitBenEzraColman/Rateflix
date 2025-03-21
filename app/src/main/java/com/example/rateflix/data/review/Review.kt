package com.example.rateflix.data.review

import android.content.Context
import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rateflix.RateflixApplication
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import java.io.Serializable

@Entity
data class Review(
    @PrimaryKey
    val id: String,
    val score: Int,
    val userId: String,
    val description: String,
    val tvShowName: String,
    var isDeleted: Boolean = false,
    var reviewImage: String? = null,
    var timestamp: Long? = null,
) : Serializable {

    companion object {
        var lastUpdated: Long
            get() {
                return RateflixApplication.Globals
                    .appContext?.getSharedPreferences("TAG", Context.MODE_PRIVATE)
                    ?.getLong(REVIEW_LAST_UPDATED, 0) ?: 0
            }
            set(value) {
                RateflixApplication.Globals
                    ?.appContext
                    ?.getSharedPreferences("TAG", Context.MODE_PRIVATE)?.edit()
                    ?.putLong(REVIEW_LAST_UPDATED, value)?.apply()
            }

        const val ID_KEY = "id"
        const val USER_ID_KEY = "userId"
        const val SCORE_KEY = "score"
        const val LAST_UPDATED_KEY = "timestamp"
        const val DESCRIPTION_KEY = "description"
        const val TV_SHOW_NAME_KEY = "tvShowName"
        const val IS_DELETED_KEY = "is_deleted"
        private const val REVIEW_LAST_UPDATED = "review_last_updated"

        fun fromJSON(json: Map<String, Any>): Review {
            Log.d(
                "Review",
                "fromJSON: $json score: ${json[SCORE_KEY]} scoreInt: ${(json[SCORE_KEY] as? Long) ?: 0}"
            )
            val id = json[ID_KEY] as? String ?: ""
            val score = (json[SCORE_KEY] as? Long)?.toInt() ?: 0
            val description = json[DESCRIPTION_KEY] as? String ?: ""
            val movieName = json[TV_SHOW_NAME_KEY] as? String ?: ""
            val isDeleted = json[IS_DELETED_KEY] as? Boolean ?: false
            val userId = json[USER_ID_KEY] as? String ?: ""
            val review = Review(id, score, userId, description, movieName, isDeleted)

            val timestamp: Timestamp? = json[LAST_UPDATED_KEY] as? Timestamp
            timestamp?.let {
                review.timestamp = it.seconds
            }

            return review
        }
    }

    val json: Map<String, Any>
        get() {
            return hashMapOf(
                ID_KEY to id,
                USER_ID_KEY to userId,
                SCORE_KEY to score,
                LAST_UPDATED_KEY to FieldValue.serverTimestamp(),
                DESCRIPTION_KEY to description,
                TV_SHOW_NAME_KEY to tvShowName,
                IS_DELETED_KEY to isDeleted
            )
        }

    val deleteJson: Map<String, Any>
        get() {
            return hashMapOf(
                IS_DELETED_KEY to true,
                LAST_UPDATED_KEY to FieldValue.serverTimestamp(),
            )
        }

    val updateJson: Map<String, Any>
        get() {
            return hashMapOf(
                SCORE_KEY to score,
                DESCRIPTION_KEY to description,
                LAST_UPDATED_KEY to FieldValue.serverTimestamp(),
            )
        }
}