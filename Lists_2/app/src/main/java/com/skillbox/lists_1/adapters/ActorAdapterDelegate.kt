package com.skillbox.lists_1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.lists_1.data.Person
import com.skillbox.lists_1.databinding.ItemActorBinding


class ActorAdapterDelegate(
    private val onItemClick: (position: Int) -> Unit
):
    AbsListItemAdapterDelegate<Person.Actor, Person, ActorAdapterDelegate.ActorHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.Actor
    }

    override fun onCreateViewHolder(parent: ViewGroup): ActorHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemActorBinding.inflate(layoutInflater, parent, false)
        return ActorHolder(binding, onItemClick)
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
        onItemClick: (position: Int) -> Unit
    ) : BasePersonHolder(binding, onItemClick) {

        fun bind(actor: Person.Actor) {
            bindMainInfo(actor.name, actor.age, actor.avatarLink, actor.isHasOscar)
        }
    }
}