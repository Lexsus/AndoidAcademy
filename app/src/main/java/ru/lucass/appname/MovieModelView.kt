package ru.lucass.appname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lucass.data.Movie
import ru.lucass.data.loadMovies

class MovieModelView : ViewModel() {
    private val _mutableMovieList = MutableLiveData<List<Movie>>();
    val mutableMovie: LiveData<List<Movie>>
        get() {
            loadData()
            return _mutableMovieList
        }

    private val _mutableMovieDetail = MutableLiveData<Movie>();
    val mutableMovieDetail: LiveData<Movie> get() = _mutableMovieDetail

    fun loadData() {
        viewModelScope.launch {
            _mutableMovieList.value = loadMovies(MovieApplication.getInstance())
        }
    }

    fun loadMovie(movie: Movie) {
//        viewModelScope.launch {
//            movie = loadMovies(MyApplication.getInstance()).first { movie -> movie.id == id }
        _mutableMovieDetail.value = movie;
        //          bind()
//        }

    }

//    fun getData(): LiveData<List<Movie>> {
//        loadData()
//        return mutableMovie
//    }

//    fun getLiveDataDetails(): LiveData<Movie> {
//        return _mutableMovieDetail
//    }


//    fun addNew() {
//        viewModelScope.launch {
//
//
//
//
//
//        }
//    }


}