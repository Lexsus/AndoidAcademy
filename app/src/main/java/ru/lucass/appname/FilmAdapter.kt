package ru.lucass.appname

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter(private val clickListener: OnRecyclerItemClicked) : RecyclerView.Adapter<FilmsViewHolder>() {
    private var films = listOf<Film>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return FilmsViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.onBind(films[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(films[position])
        }
    }

    override fun getItemCount(): Int = films.size

    fun bindFilms(newFilms: List<Film>) {
        films = newFilms
        notifyDataSetChanged()
    }
}

class FilmsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_film_avatar)
    private val PG: TextView = itemView.findViewById(R.id.tv_PG)
    private val filmName: TextView = itemView.findViewById(R.id.tv_FilmName)

    fun onBind(film: Film) {
        val uri = film.avatar
        val imageResource: Int = context.resources.getIdentifier(uri, null, context.packageName)

        val res: Drawable = context.resources.getDrawable(imageResource,null)
        avatar.setImageDrawable(res)
        //PG.text = context.getString(film.nameRes)
        PG.text = film.pg
        filmName.text = film.name
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnRecyclerItemClicked {
    fun onClick(film: Film)
}