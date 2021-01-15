package ru.lucass.appname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lucass.appname.repository.NetworkModule
import ru.lucass.data.Movie

class MovieModelView : ViewModel() {
    val networkModule = NetworkModule()
    private val _mutableMovieList = MutableLiveData<List<Movie>>()
    val mutableMovie: LiveData<List<Movie>>
        get() {
            loadData()
            return _mutableMovieList
        }

    private val _mutableMovieDetail = MutableLiveData<Movie>();
    val mutableMovieDetail: LiveData<Movie> get() = _mutableMovieDetail

    fun loadData() {
        viewModelScope.launch {
            _mutableMovieList.value = networkModule.loadMovies()
        }
    }

    fun loadMovie(movie: Movie) {
        _mutableMovieDetail.value = movie;
    }
}