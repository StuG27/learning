package com.skillbox.lists_1.adapters

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.lists_1.R
import com.skillbox.lists_1.data.Person
import com.skillbox.lists_1.extensions.inflate

class ActorAdapterDelegate(
    private val onItemClick: (position: Int) -> Unit
):
    AbsListItemAdapterDelegate<Person.Actor, Person, ActorAdapterDelegate.ActorHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.Actor
    }

    override fun onCreateViewHolder(parent: ViewGroup): ActorHolder {
        return ActorHolder(parent.inflate(R.layout.item_actor), onItemClick)
    }

    override fun onBindViewHolder(
        item: Person.Actor,
        holder: ActorHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ActorHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BasePersonHolder(view, onItemClick) {

        fun bind(actor: Person.Actor) {
            bindMainInfo(actor.name, actor.age, actor.avatarLink, actor.isHasOscar)
        }
    }
}