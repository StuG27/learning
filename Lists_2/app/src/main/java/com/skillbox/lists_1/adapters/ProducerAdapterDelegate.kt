package com.skillbox.lists_1.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.lists_1.R
import com.skillbox.lists_1.data.Person
import com.skillbox.lists_1.extensions.inflate

class ProducerAdapterDelegate(
    private val onItemClick: (position: Int) -> Unit
):
    AbsListItemAdapterDelegate<Person.Producer, Person, ProducerAdapterDelegate.ProducerHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.Producer
    }

    override fun onCreateViewHolder(parent: ViewGroup): ProducerHolder {
        return ProducerHolder(parent.inflate(R.layout.item_producer), onItemClick)
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
        onItemClick: (position: Int) -> Unit
    ) : BasePersonHolder(view, onItemClick) {

        private val context = view.context
        private val tVBestFilm: TextView = view.findViewById(R.id.tVBestFilm)

        fun bind(producer: Person.Producer) {
            bindMainInfo(producer.name, producer.age, producer.avatarLink, producer.isHasOscar)
            tVBestFilm.text = context.getString(R.string.best_film, producer.bestFilm)
        }
    }
}