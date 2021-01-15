package ru.lucass.appname.repository

import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorsJson(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String? = null
)
