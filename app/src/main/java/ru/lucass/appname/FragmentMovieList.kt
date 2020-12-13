package ru.lucass.appname

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class FragmentMovieList: Fragment() {
//    private var listener: ClickListener? = null
    private var imageMovie: ImageView? = null
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
//        imageMovie = view.findViewById<ImageView>(R.id.imageViewMovie).apply {
//            setOnClickListener{listener?.nextfragment()};
//        }
        recycler = view.findViewById(R.id.rv_films)
        recycler?.adapter = FilmAdapter(clickListener)
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? FilmAdapter)?.apply {
            bindFilms(FilmDataSource().getFilms())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is ClickListener)
//        {
//            listener = context;
//        }
    }

    override fun onDetach() {
        super.onDetach()
        recycler = null
        //listener = null;
    }

    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(film: Film) {
            doOnClick(film)
        }
    }

    private fun doOnClick(film: Film) {
        recycler?.let { rv ->
            Snackbar.make(
                    rv,
                    getString(R.string.fragment_films_chosen_text, film.name),
                    Snackbar.LENGTH_SHORT)
                    .show()
        }
        (activity as MainActivity?)?.nextfragment()
    }
//    interface ClickListener {
//        fun nextfragment()
//    }


    companion object {
        fun newInstance(
        ): FragmentMovieList {
            return FragmentMovieList()
        }
    }
}