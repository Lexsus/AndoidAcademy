package ru.lucass.appname

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FragmentMovieList: Fragment() {
    private var listener: ClickListener? = null
    private var imageMovie: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageMovie = view.findViewById<ImageView>(R.id.imageViewMovie).apply {
            setOnClickListener{listener?.nextfragment()};
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListener)
        {
            listener = context;
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null;
    }

    interface ClickListener {
        fun nextfragment()
    }


    companion object {
        fun newInstance(
        ): FragmentMovieList {
            return FragmentMovieList()
        }
    }
}