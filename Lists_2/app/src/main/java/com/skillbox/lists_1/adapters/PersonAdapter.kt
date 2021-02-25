package com.skillbox.lists_1.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.skillbox.lists_1.data.Person



class PersonAdapter(
    onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //ListAdapter

    private val differ = AsyncListDiffer(this, PersonDiffUtilCallback())

    private val delegatesManager = AdapterDelegatesManager<List<Person>>()

    init {
        delegatesManager.addDelegate(ActorAdapterDelegate(onItemClick))
            .addDelegate(ProducerAdapterDelegate(onItemClick))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return delegatesManager.onBindViewHolder(differ.currentList, position, holder)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(differ.currentList, position)
    }

    fun update(newPersons: List<Person>) {
        differ.submitList(newPersons)
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

    companion object {
        private const val TYPE_ACTOR = 0
        private const val TYPE_PRODUCER = 1
    }
}