package com.skillbox.lists_1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.lists_1.R
import com.skillbox.lists_1.data.Person
import com.skillbox.lists_1.databinding.ItemProducerBinding


class ProducerAdapterDelegate(
    private val onItemClick: (position: Int) -> Unit
):
    AbsListItemAdapterDelegate<Person.Producer, Person, ProducerAdapterDelegate.ProducerHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.Producer
    }

    override fun onCreateViewHolder(parent: ViewGroup): ProducerHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProducerBinding.inflate(layoutInflater, parent, false)
        return ProducerHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(
        item: Person.Producer,
        holder: ProducerHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ProducerHolder(
        binding: ItemProducerBinding,
        onItemClick: (position: Int) -> Unit
    ) : BasePersonHolder(binding, onItemClick) {

        private val context = binding.root.context
        private val tVBestFilm = binding.tVBestFilm

        fun bind(producer: Person.Producer) {
            bindMainInfo(producer.name, producer.age, producer.avatarLink, producer.isHasOscar)
            tVBestFilm.text = context.getString(R.string.best_film, producer.bestFilm)
        }
    }
}