package ru.lucass.appname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class FragmentMovieDetails: Fragment() {
    private var recycler: RecyclerView? = null

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
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? ActorAdapter)?.apply {
            bindActors(ActorsDataSource().getActors())
        }
    }
    companion object {
        fun newInstance(
        ): FragmentMovieDetails {
            return FragmentMovieDetails()
        }
    }
}