package com.skillbox.lists_1.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.skillbox.lists_1.R
import com.skillbox.lists_1.data.Person
import com.skillbox.lists_1.extensions.inflate

class PersonAdapter(
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var persons: List<Person> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ACTOR -> ActorHolder(parent.inflate(R.layout.item_actor), onItemClick)
            TYPE_PRODUCER -> ProducerHolder(parent.inflate(R.layout.item_producer), onItemClick)
            else -> error("Некорректный ViewType = $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ActorHolder -> {
                val actor = persons[position].let { it as? Person.Actor }
                    ?: error("Персона на позиции $position не актёр")
                holder.bind(actor)
            }
            is ProducerHolder -> {
                val producer = persons[position].let { it as? Person.Producer }
                    ?: error("Персона на позиции $position не режиссёр")
                holder.bind(producer)
            }
            else -> error("Некорректный ViewHolder = $holder")
        }
    }

    override fun getItemCount(): Int = persons.size

    override fun getItemViewType(position: Int): Int {
        return when (persons[position]) {
            is Person.Actor -> TYPE_ACTOR
            is Person.Producer -> TYPE_PRODUCER
        }
    }

    fun update(newPersons: List<Person>) {
        persons = newPersons
    }

    abstract class BasePersonHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        private val context = view.context
        private val tVName: TextView = view.findViewById(R.id.tVName)
        private val tVAge: TextView = view.findViewById(R.id.tVAge)
        private val tVIsHasOscar: TextView = view.findViewById(R.id.tVIsHasOscar)
        private val iVAvatar: ImageView = view.findViewById(R.id.iVAvatar)

        protected fun bindMainInfo(
            name: String,
            age: Int,
            avatarLink: String,
            isHasOscar: Boolean
        ) {
            tVName.text = name
            tVAge.text = context.getString(R.string.age, age)
            if (!isHasOscar) {
                tVIsHasOscar.visibility = View.GONE
            } else {
                tVIsHasOscar.visibility = View.VISIBLE
            }
            Glide
                .with(itemView)
                .load(avatarLink)
                .placeholder(R.drawable.ic_baseline_portrait_24)
                .error(R.drawable.ic_baseline_portrait_24)
                .into(iVAvatar)
        }
    }

    class ActorHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BasePersonHolder(view, onItemClick) {

        fun bind(actor: Person.Actor) {
            bindMainInfo(actor.name, actor.age, actor.avatarLink, actor.isHasOscar)
        }
    }

    class ProducerHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BasePersonHolder(view, onItemClick) {

        private val context = view.context
        private val tVBestFilm: TextView = view.findViewById(R.id.tVBestFilm)

        fun bind(producer: Person.Producer) {
            bindMainInfo(producer.name, producer.age, producer.avatarLink, producer.isHasOscar)
            tVBestFilm.text = context.getString(R.string.best_film, producer.bestFilm)
        }
    }

    companion object {
        private const val TYPE_ACTOR = 0
        private const val TYPE_PRODUCER = 1
    }
}