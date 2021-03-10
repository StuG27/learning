package com.skillbox.viewmodelandnavigation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.viewmodelandnavigation.data.Person


class PersonAdapter(
        onItemClick: (id: Long) -> Unit,
        onLongItemClick: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Person>(PersonDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(ActorAdapterDelegate(onItemClick, onLongItemClick))
                .addDelegate(ProducerAdapterDelegate(onItemClick, onLongItemClick))
    }

    class PersonDiffUtilCallback : DiffUtil.ItemCallback<Person>() {

        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return when {
                oldItem is Person.Producer && newItem is Person.Producer -> oldItem.id == newItem.id
                oldItem is Person.Actor && newItem is Person.Actor -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }
}