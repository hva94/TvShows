package com.hvasoft.mubi.data.local_db.entities

data class SeasonEntity(
    val id: Int,
    val name: String,
    val posterPath: String? = "",
    val totalEpisodes: Int?,
    val overview: String,
    val seasonNumber: Int
)
