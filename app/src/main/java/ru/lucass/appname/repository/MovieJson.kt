package ru.lucass.appname.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lucass.data.Actor
import ru.lucass.data.Genre

@Serializable
data class MovieJson(
    val id: Int,
    @SerialName("original_title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val poster: String,
    @SerialName("backdrop_path")
    val backdrop: String,
    @SerialName("popularity")
    val ratings: Float,
    @SerialName("vote_average")
    val votesCount: Float,
    val adult: Boolean,
//    val minimumAge: Int,
//    val runtime: Int,
    @SerialName("genre_ids")
    val genresIds: List<Int>,
//    val actors: List<Actor>
)

