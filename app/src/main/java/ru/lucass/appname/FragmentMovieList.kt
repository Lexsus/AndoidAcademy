package ru.lucass.appname

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.lucass.data.Movie


class FragmentMovieList : Fragment(R.layout.fragment_movies_list) {
    private var imageMovie: ImageView? = null

    private var adapter: MovieAdapter? = null
    private val scope = CoroutineScope(Dispatchers.IO)
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler: RecyclerView? = view.findViewById(R.id.rv_films)
        adapter = MovieAdapter(::doOnClick)
        recycler?.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner, Observer(::updateData))
        viewModel.loadMovies()
    }

    private fun updateData(movies: List<Movie>) {
        adapter?.apply {
            bindFilms(movies)
        }
    }

    private fun doOnClick(movie: Movie) {
        (activity as? Navigator)?.nextFragment(movie)
    }

    companion object {
        fun newInstance() = FragmentMovieList()
    }
}