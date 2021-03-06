package com.skillbox.viewmodelandnavigation.adapters

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.myapplication.R
import com.skillbox.viewmodelandnavigation.data.Person

import com.skillbox.viewmodelandnavigation.extensions.inflate

class ActorAdapterDelegate(
    private val onItemClick: (id: Long) -> Unit,
    private val onLongItemClick: (position: Int) -> Unit
):
    AbsListItemAdapterDelegate<Person.Actor, Person, ActorAdapterDelegate.ActorHolder>() {

    override fun isForViewType(item: Person, items: MutableList<Person>, position: Int): Boolean {
        return item is Person.Actor
    }

    override fun onCreateViewHolder(parent: ViewGroup): ActorHolder {
        return ActorHolder(parent.inflate(R.layout.item_actor), onItemClick, onLongItemClick)
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
        onItemClick: (id: Long) -> Unit,
        onLongItemClick: (position: Int) -> Unit
    ) : BasePersonHolder(view, onItemClick, onLongItemClick) {

        fun bind(actor: Person.Actor) {
            bindMainInfo(actor.id, actor.name, actor.age, actor.avatarLink, actor.isHasOscar)
        }
    }
}