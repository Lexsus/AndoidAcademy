package ru.lucass.appname

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.lucass.data.Movie

class FilmAdapter(private val clickListener: OnRecyclerItemClicked) : RecyclerView.Adapter<FilmsViewHolder>() {
    private var movies = listOf<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return FilmsViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(movies[position])
//            (getContext)

        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindFilms(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

class FilmsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_film_avatar)
    private val PG: TextView = itemView.findViewById(R.id.tv_PG)
    private val filmName: TextView = itemView.findViewById(R.id.tv_FilmName)

    fun onBind(film: Movie) {
//        val uri = film.avatar
//        val imageResource: Int = context.resources.getIdentifier(uri, null, context.packageName)

//        val res: Drawable = context.resources.getDrawable(imageResource,null)
//        avatar.setImageDrawable(res)
        //PG.text = context.getString(film.nameRes)
        Glide.with(context)
                .load(film.backdrop)
                .apply(imageOption)
                .into(avatar)

        PG.text = film.minimumAge.toString()
        filmName.text = film.title
    }

    companion object {
        private val imageOption = RequestOptions()
                .placeholder(R.drawable.ic_avatar_placeholder)
                .fallback(R.drawable.ic_avatar_placeholder)
                .circleCrop()
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnRecyclerItemClicked {
    fun onClick(film: Movie)
}