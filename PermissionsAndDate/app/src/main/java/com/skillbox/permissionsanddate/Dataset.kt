package com.skillbox.permissionsanddate

import org.threeten.bp.Instant

sealed class DataSet {

    data class DataSetWithLocation(
        val id: Long,
        val createdAt: Instant,
        val latitude: String,
        val longitude: String,
        val altitude: String,
        val link: String
    ) : DataSet()

}
