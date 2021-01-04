package ru.lucass.appname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.lucass.data.Movie

class FragmentMovieDetails(movie:Movie): Fragment() {
    private var recycler: RecyclerView? = null
//    private var backGround: ImageView? = null
    private val movieSelect = movie
    private var viewModel: MovieModelView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieModelView::class.java)
        viewModel?.loadMovie(movieSelect)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rv_actors)
        viewModel?.mutableMovieDetail?.observe(this.viewLifecycleOwner, Observer {
            initRecycler(view,it)
        })
        recycler?.adapter = ActorAdapter()
    }

    private fun initRecycler(view: View,movie:Movie) {
        val backGround:ImageView = view.findViewById(R.id.iv_BackGround)
        val let = context?.let {
            Glide.with(it)
                .load(movie.backdrop)
                .apply(imageOption)
                .into(backGround)
        }
        val textViewPG = view.findViewById<TextView>(R.id.PG)
        textViewPG.text = movie.minimumAge.toString()+"+"

        val textViewDevastating:TextView = view.findViewById(R.id.textViewDevastate)
        textViewDevastating.text = movie.overview

        val textViewTitle:TextView = view.findViewById(R.id.textViewTitle)
        textViewTitle.text = movie.title

        val textViewTag:TextView = view.findViewById(R.id.textViewTag)
        textViewTag.text = movie.genres.joinToString { it -> it.name }
        (recycler?.adapter as? ActorAdapter)?.apply {
            bindActors(movie.actors)
        }
    }

    override fun onStart() {
        super.onStart()

//        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? ActorAdapter)?.apply {
            bindActors(movieSelect.actors)
        }
    }
    companion object {
        fun newInstance(movie:Movie
        ): FragmentMovieDetails {
            return FragmentMovieDetails(movie)
        }

        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .fallback(R.drawable.ic_avatar_placeholder)
    }


}