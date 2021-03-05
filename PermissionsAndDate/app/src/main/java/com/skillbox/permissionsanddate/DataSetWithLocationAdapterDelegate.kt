package com.skillbox.permissionsanddate

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.permissionsanddate.databinding.ItemMessageWithDateBinding
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class DataSetWithLocationAdapterDelegate(
        private val onItemClick: (position: Int) -> Unit
):
    AbsListItemAdapterDelegate<DataSet, DataSet, DataSetWithLocationAdapterDelegate.DataSetWithLocationHolder>() {

    override fun isForViewType(item: DataSet, items: MutableList<DataSet>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): DataSetWithLocationHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMessageWithDateBinding.inflate(layoutInflater, parent, false)
        return DataSetWithLocationHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(
            item: DataSet,
            holder: DataSetWithLocationHolder,
            payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class DataSetWithLocationHolder(binding: ItemMessageWithDateBinding, onItemClick: (position: Int) -> Unit) : BaseDataSetHolder(binding, onItemClick) {

        private  val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
            .withZone(ZoneId.systemDefault())

        fun bind(dataSet: DataSet) {
            bindMainInfo(formatter.format(dataSet.createdAt), dataSet.latitude, dataSet.longitude, dataSet.altitude, dataSet.link)
        }
    }
}