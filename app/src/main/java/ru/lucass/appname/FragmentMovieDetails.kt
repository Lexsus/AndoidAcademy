package ru.lucass.appname

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.lucass.data.Movie

private const val ARG_PARAM1 = "movie"

class FragmentMovieDetails() : Fragment(R.layout.fragment_movies_details) {

    private var recycler: RecyclerView? = null
    private val movieSelect:Movie? by lazy{
        arguments?.getParcelable(ARG_PARAM1)
    }
    private val viewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         viewModel.loadMovie(movieSelect)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recycler = view.findViewById(R.id.rv_actors)
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            updateData(it)
        })
        recycler?.adapter = ActorAdapter()
    }

    private fun updateData( movie: Movie) {
        val backGround: ImageView? = view?.findViewById(R.id.iv_background)
        if (backGround != null) {
            Glide.with(this)
                .load(movie.backdrop)
                .apply(GlideOption.imageOption)
                .into(backGround)
        }
        view?.findViewById<TextView>(R.id.age_rating_details)?.text = movie.minimumAge.toString() + "+"
        view?.findViewById<TextView>(R.id.tv_devastate)?.text = movie.overview
        view?.findViewById<TextView>(R.id.tv_title)?.text = movie.title
        view?.findViewById<TextView>(R.id.tv_tag)?.text =
            movie.genres.joinToString { it.name }
        (recycler?.adapter as? ActorAdapter)?.apply {
            bindActors(movie.actors)
        }
    }

    companion object {
        fun newInstance(param1: Movie) =
            FragmentMovieDetails().apply {
                arguments = bundleOf(
                    ARG_PARAM1 to  param1
                )
            }
    }
}