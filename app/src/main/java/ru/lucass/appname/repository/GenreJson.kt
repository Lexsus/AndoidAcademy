package ru.lucass.appname.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreJson (
    val id: Int,
    @SerialName("name")
    val name: String
)