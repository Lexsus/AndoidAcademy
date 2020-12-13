package ru.lucass.appname

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//TODO разобраться со сдвигом RV от CAST
//TODO 13+ сделать по figma
class ActorAdapter: RecyclerView.Adapter<ActorsViewHolder>() {
    private var actors = listOf<Actor>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }
}

class ActorsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_avatar)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)

    fun onBind(actor: Actor) {
        val uri = actor.avatar
        val imageResource: Int = context.resources.getIdentifier(uri, null, context.packageName)

        val res: Drawable = context.resources.getDrawable(imageResource,null)
        avatar.setImageDrawable(res)
        name.text = context.getString(actor.nameRes)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context