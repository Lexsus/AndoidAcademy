package ru.lucass.appname.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsGenres (
    @SerialName("genres")
    val results:List<GenreJson>
)
