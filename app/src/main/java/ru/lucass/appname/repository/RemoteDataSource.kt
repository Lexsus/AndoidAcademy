package ru.lucass.appname.repository

interface RemoteDataSource {

    suspend fun getMovies(type: String): ResultsMovie

}
