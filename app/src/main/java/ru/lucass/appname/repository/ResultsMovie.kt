package ru.lucass.appname.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsMovie(
    @SerialName("results")
    val results:List<MovieJson>
)