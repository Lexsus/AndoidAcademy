package ru.lucass.appname

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import ru.lucass.data.Movie
import ru.lucass.data.loadMovies


class FragmentMovieList: Fragment() {
//    private var listener: ClickListener? = null
    private var imageMovie: ImageView? = null
    private var recycler: RecyclerView? = null
//    private lateinit var  movies:List<Movie>
    private val scope = CoroutineScope(Dispatchers.IO)
    private var viewModel: MovieModelView? = null
    private var liveData: LiveData<List<Movie>>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieModelView::class.java)
        liveData = viewModel?.mutableMovie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.rv_films)
        recycler?.adapter = MovieAdapter(clickListener)

        viewModel?.mutableMovie?.observe(this.viewLifecycleOwner, Observer<List<Movie>> {
            updateData(it)
        })
    }

    override fun onStart() {
        super.onStart()


    }

    private fun updateData(movies:List<Movie>) {

        (recycler?.adapter as? MovieAdapter)?.apply {
            bindFilms(movies)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        recycler = null
        //listener = null;
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(film: Movie) {
            doOnClick(film)
        }
    }

    private fun doOnClick(movie: Movie) {
        recycler?.let { rv ->
            Snackbar.make(
                    rv,
                    getString(R.string.fragment_films_chosen_text, movie.title),
                    Snackbar.LENGTH_SHORT)
                    .show()
        }
        (activity as MainActivity?)?.nextfragment(movie)
    }

    companion object {
        fun newInstance(
        ): FragmentMovieList {
            return FragmentMovieList()
        }
    }
}