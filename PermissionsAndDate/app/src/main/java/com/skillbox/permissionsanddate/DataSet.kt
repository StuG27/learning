package com.skillbox.permissionsanddate

import org.threeten.bp.Instant


data class DataSet(
        val id: Long,
        var createdAt: Instant,
        val latitude: String,
        val longitude: String,
        val altitude: String,
        val link: String
        )

