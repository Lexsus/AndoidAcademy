package ru.lucass.appname.repository

class RemoteDataSourceImpl(private val service: TMDBService) : RemoteDataSource {

    override suspend fun getMovies(type: String) = service.getMovies(type)

}