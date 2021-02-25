package com.skillbox.lists_1.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.lists_1.data.Person

class PersonAdapterShort(
    onItemClick: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Person>(PersonDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(ActorAdapterDelegate(onItemClick))
            .addDelegate(ProducerAdapterDelegate(onItemClick))
    }

    class PersonDiffUtilCallback : DiffUtil.ItemCallback<Person>() {

        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            //Сравнение по id вызывается первым
            return when {
                oldItem is Person.Producer && newItem is Person.Producer -> oldItem.id == newItem.id
                oldItem is Person.Actor && newItem is Person.Actor -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            //Сравнение по содержанию вызывается вторым
            return oldItem == newItem // ТК DATA класс - то это сравнивает все поля
        }
    }
}