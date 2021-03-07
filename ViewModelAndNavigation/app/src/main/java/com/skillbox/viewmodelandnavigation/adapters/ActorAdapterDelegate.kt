package com.skillbox.viewmodelandnavigation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.myapplication.databinding.ItemActorBinding
import com.skillbox.viewmodelandnavigation.data.Person


class ActorAdapterDelegate(
    private val onItemClick: (id: Long) -> Unit,
    private val onLongItemClick: (position: Int) -> Unit
):
    AbsListItemAdapterDelegate<Person.Actor, Person, ActorAdapterDelegate.ActorHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.Actor
    }

    override fun onCreateViewHolder(parent: ViewGroup): ActorHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemActorBinding.inflate(layoutInflater, parent, false)
        return ActorHolder(binding, onItemClick, onLongItemClick)
    }

    override fun onBindViewHolder(
        item: Person.Actor,
        holder: ActorHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ActorHolder(
            binding: ItemActorBinding,
            onItemClick: (id: Long) -> Unit,
            onLongItemClick: (position: Int) -> Unit
    ) : BasePersonHolder(binding, onItemClick, onLongItemClick) {

        fun bind(actor: Person.Actor) {
            bindMainInfo(actor.id, actor.name, actor.age, actor.avatarLink, actor.isHasOscar)
        }
    }
}