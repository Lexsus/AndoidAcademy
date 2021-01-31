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
    //    private var listener: ClickListener? = null
    private var imageMovie: ImageView? = null
    private var recycler: RecyclerView? = null
    private val scope = CoroutineScope(Dispatchers.IO)
    private val viewModel: MovieViewModel by viewModels()
    private var liveData: LiveData<List<Movie>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveData = viewModel.movies
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.rv_films)
        recycler?.adapter = MovieAdapter(clickListener)

        viewModel.movies.observe(viewLifecycleOwner, Observer(::updateData))
    }

    private fun updateData(movies: List<Movie>) {
        (recycler?.adapter as? MovieAdapter)?.apply {
            bindFilms(movies)
        }
    }

    override fun onDetach() {
        super.onDetach()
        recycler = null
    }

    private val clickListener = ::doOnClick

    private fun doOnClick(movie: Movie) {
        (activity as Navigator?)?.nextFragment(movie)
    }

    companion object {
        fun newInstance(): FragmentMovieList = FragmentMovieList()
    }
}