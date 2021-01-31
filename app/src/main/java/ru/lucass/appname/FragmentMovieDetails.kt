package ru.lucass.appname

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.lucass.data.Movie

private const val ARG_PARAM1 = "movie"

class FragmentMovieDetails() : Fragment(R.layout.fragment_movies_details) {

    private var recycler: RecyclerView? = null
    private var movieSelect: Movie? = null
    private val viewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieSelect = it.getParcelable(ARG_PARAM1)
        }
        viewModel.loadMovie(movieSelect)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recycler = view.findViewById(R.id.rv_actors)
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            updateData(view, it)
        })
        recycler?.adapter = ActorAdapter()
    }

    private fun updateData(view: View, movie: Movie) {
        val backGround: ImageView = view.findViewById(R.id.iv_background)
        Glide.with(this)
            .load(movie.backdrop)
            .apply(GlideOption.imageOption)
            .into(backGround)
        view.findViewById<TextView>(R.id.age_rating_details)?.text = movie.minimumAge.toString() + "+"
        view.findViewById<TextView>(R.id.tv_devastate)?.text = movie.overview
        view.findViewById<TextView>(R.id.tv_title)?.text = movie.title
        view.findViewById<TextView>(R.id.tv_tag)?.text =
            movie.genres.joinToString { it -> it.name }
        (recycler?.adapter as? ActorAdapter)?.apply {
            bindActors(movie.actors)
        }
    }

    companion object {
        fun newInstance(param1: Movie) =
            FragmentMovieDetails().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                }
            }

//        private val imageOption = RequestOptions()
//            .placeholder(R.drawable.ic_avatar_placeholder)
//            .fallback(R.drawable.ic_avatar_placeholder)
    }
}