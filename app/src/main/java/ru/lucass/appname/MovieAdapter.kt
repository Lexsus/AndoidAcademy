package ru.lucass.appname

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.lucass.data.Movie

class MovieAdapter(private val clickListener: OnRecyclerItemClicked) :
    RecyclerView.Adapter<FilmsViewHolder>() {
    private var movies = listOf<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return FilmsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindFilms(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

class FilmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_film_avatar)
    private val ageRating: TextView = itemView.findViewById(R.id.tv_age_rating)
    private val filmName: TextView = itemView.findViewById(R.id.tv_film_name)
    private val tagMovie: TextView = itemView.findViewById(R.id.tv_tag_movie)

    fun onBind(film: Movie) {
        Glide.with(context)
            .load(film.poster)
            .apply(GlideOption.imageOption)
            .into(avatar)

        ageRating.text = film.minimumAge.toString() + "+"
        filmName.text = film.title
        tagMovie.text = film.genres.joinToString { it -> it.name }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

fun interface OnRecyclerItemClicked {
    fun onClick(film: Movie)
}