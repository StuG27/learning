package com.skillbox.permissionsanddate

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.permissionsanddate.extentions.inflate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class DataSetWithLocationAdapterDelegate(
        private val onItemClick: (position: Int) -> Unit
):
    AbsListItemAdapterDelegate<DataSet.DataSetWithLocation, DataSet, DataSetWithLocationAdapterDelegate.DataSetWithLocationHolder>() {

    override fun isForViewType(item: DataSet, items: MutableList<DataSet>, position: Int): Boolean {
        return item is DataSet.DataSetWithLocation
    }

    override fun onCreateViewHolder(parent: ViewGroup): DataSetWithLocationHolder {
        return DataSetWithLocationHolder(parent.inflate(R.layout.item_message_with_date), onItemClick)
    }

    override fun onBindViewHolder(
            item: DataSet.DataSetWithLocation,
            holder: DataSetWithLocationHolder,
            payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class DataSetWithLocationHolder(view: View, onItemClick: (position: Int) -> Unit) : BaseDataSetHolder(view, onItemClick) {

        private  val formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")
            .withZone(ZoneId.systemDefault())

        fun bind(dataSet: DataSet.DataSetWithLocation) {
            bindMainInfo(formatter.format(dataSet.createdAt), dataSet.latitude, dataSet.longitude, dataSet.altitude, dataSet.link)
        }
    }
}