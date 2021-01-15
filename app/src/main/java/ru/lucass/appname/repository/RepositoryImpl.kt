package ru.lucass.appname.repository

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    override suspend fun getMovies(type: String) =
        remoteDataSource.getMovies(type)

}