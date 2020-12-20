package ru.lucass.appname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.lucass.data.Movie

class FragmentMovieDetails(movie:Movie): Fragment() {
    private var recycler: RecyclerView? = null
//    private var backGround: ImageView? = null
    private val movieSelect = movie
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rv_actors)

        recycler?.adapter = ActorAdapter()

        val backGround:ImageView = view.findViewById(R.id.iv_BackGround)
        val let = context?.let {
            Glide.with(it)
                .load(movieSelect.backdrop)
                .apply(imageOption)
                .into(backGround)
        }
        val textViewPG = view.findViewById<TextView>(R.id.PG)
        textViewPG.text = movieSelect.minimumAge.toString()+"+"

        val textViewDevastating:TextView = view.findViewById(R.id.textViewDevastate)
        textViewDevastating.text = movieSelect.overview

        val textViewTitle:TextView = view.findViewById(R.id.textViewTitle)
        textViewTitle.text = movieSelect.title

        val textViewTag:TextView = view.findViewById(R.id.textViewTag)
        textViewTag.text = movieSelect.genres.joinToString { it -> it.name }

    }

    override fun onStart() {
        super.onStart()

        updateData()
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