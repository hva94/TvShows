package com.hvasoft.mubi.domain.model

data class Season(
    val id: Int,
    val name: String,
    val posterPath: String? = "",
    val totalEpisodes: Int?,
    val overview: String,
    val seasonNumber: Int
)