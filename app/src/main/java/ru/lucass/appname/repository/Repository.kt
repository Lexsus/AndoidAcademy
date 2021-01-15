package ru.lucass.appname.repository

interface Repository {
    suspend fun getMovies(type: String) : ResultsMovie
}