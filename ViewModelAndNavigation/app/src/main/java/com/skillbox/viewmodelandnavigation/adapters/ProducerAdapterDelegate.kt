package com.skillbox.viewmodelandnavigation.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.myapplication.R
import com.skillbox.viewmodelandnavigation.data.Person
import com.skillbox.viewmodelandnavigation.extensions.inflate
import java.text.FieldPosition

class ProducerAdapterDelegate(
    private val onItemClick: (id: Long) -> Unit,
    private val onLongItemClick: (position: Int) -> Unit
):
    AbsListItemAdapterDelegate<Person.Producer, Person, ProducerAdapterDelegate.ProducerHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.Producer
    }

    override fun onCreateViewHolder(parent: ViewGroup): ProducerHolder {
        return ProducerHolder(parent.inflate(R.layout.item_producer), onItemClick, onLongItemClick)
    }

    override fun onBindViewHolder(
        item: Person.Producer,
        holder: ProducerHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ProducerHolder(
        view: View,
        onItemClick: (id: Long) -> Unit,
        onLongItemClick: (position: Int) -> Unit
    ) : BasePersonHolder(view, onItemClick, onLongItemClick) {

        private val context = view.context
        private val tVBestFilm: TextView = view.findViewById(R.id.tVBestFilm)

        fun bind(producer: Person.Producer) {
            bindMainInfo(producer.id, producer.name, producer.age, producer.avatarLink, producer.isHasOscar)
            tVBestFilm.text = context.getString(R.string.best_film, producer.bestFilm)
        }
    }
}