package ru.lucass.appname.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResultsActors (
    @SerialName("cast")
    val results:List<ActorsJson>
)