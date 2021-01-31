package ru.lucass.appname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lucass.data.Movie
import ru.lucass.data.loadMovies

class MovieViewModel : ViewModel() {

    private val _movie = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() {
            loadData()
            return _movie
        }
    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> get() = _movieDetail

    private fun loadData() {
        viewModelScope.launch {
            _movie.value = loadMovies(MovieApplication.getInstance())
        }
    }

    fun loadMovie(movie: Movie?) {
        _movieDetail.value = movie;
    }
}