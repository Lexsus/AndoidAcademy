package ru.lucass.appname.repository

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.lucass.data.Genre

interface TMDBService {
    @GET("discover/movie?language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getMovies(@Query("api_key") type: String): ResultsMovie
    @GET("genre/movie/list?language=en-U")
    suspend fun getGenres(@Query("api_key") type: String): ResultsGenres
    @GET("movie/{movie_id}/credits?language=en-U")
    suspend fun getActors(@Path("movie_id") movie_id:Int,@Query("api_key") type: String): ResultsActors
}