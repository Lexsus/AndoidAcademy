package ru.lucass.appname.repository

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import ru.lucass.appname.BuildConfig
import ru.lucass.data.*

private const val CONNECT_TIMEOUT = 10L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L
private const val BASE_URL = "https://api.themoviedb.org/3/"
const val apiKey = "371c5be25923414e9a7a6709f0cfc675"

class NetworkModule {

    private suspend fun loadGenres(): List<Genre> {
        val resultsGenresJson = RetrofitModule.movieMyApi.getGenres(apiKey).results
        return resultsGenresJson.map {
            Genre(id = it.id, name = it.name)
        }
    }

    private suspend fun loadActors(movieId: Int): List<Actor> {
        val resultsActorsJson =
            RetrofitModule.movieMyApi.getActors(movieId, apiKey).results
        return resultsActorsJson.map { Actor(it.id, it.name, getActorPosterUrl(it)) }.take(7)
            .toList()
    }

    public suspend fun loadMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val resultsMovieJson = RetrofitModule.movieMyApi.getMovies(apiKey)
        var resultsGenres = loadGenres();


        resultsMovieJson.results.map { jsonMovie ->
            val resultsActors = loadActors(jsonMovie.id)
            resultsActors.forEach { Log.d("Actors", it.picture) }

            val genresMap = resultsGenres.associateBy { it.id }
            Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                overview = jsonMovie.overview,
                poster = getPosterUrl(jsonMovie),
                backdrop = getBackDropUrl(jsonMovie),
                ratings = jsonMovie.ratings,
                numberOfRatings = jsonMovie.votesCount.toInt(),
                minimumAge = if (jsonMovie.adult) 16 else 13,
                runtime = 120,
                genres = jsonMovie.genresIds.map {
                    genresMap[it] ?: throw IllegalArgumentException("Genre not found")
                },
                actors = resultsActors
            )
        }
    }

    private object RetrofitModule {
        private val json = Json {
            ignoreUnknownKeys = true
        }
        private val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        val movieMyApi: TMDBService = retrofit.create()
    }

    private fun getActorPosterUrl(it: ActorsJson) = "${BuildConfig.BASE_IMAGE_URL}${it.picture}"
    private fun getPosterUrl(it: MovieJson) = "${BuildConfig.BASE_IMAGE_URL}${it.poster}"
    private fun getBackDropUrl(it: MovieJson) = "${BuildConfig.BASE_IMAGE_URL}${it.backdrop}"
}