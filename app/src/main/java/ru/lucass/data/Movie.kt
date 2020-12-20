package ru.lucass.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.lucass.data.Actor
import ru.lucass.data.Genre
@Parcelize
data class Movie(
        val id: Int,
        val title: String,
        val overview: String,
        val poster: String,
        val backdrop: String,
        val ratings: Float,
        val numberOfRatings: Int,
        val minimumAge: Int,
        val runtime: Int,
        val genres: List<Genre>,
        val actors: List<Actor>
) : Parcelable