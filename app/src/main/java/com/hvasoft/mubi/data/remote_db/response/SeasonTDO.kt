package com.hvasoft.mubi.data.remote_db.response

import com.hvasoft.mubi.domain.model.Season
import com.squareup.moshi.Json

data class SeasonTDO(
    val id: Int,
    val name: String,
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "episode_count")
    val totalEpisodes: Int?,
    @Json(name = "season_number")
    val seasonNumber: Int,
    val overview: String
) {
    fun toDomain(): Season =
        Season(
            id,
            name,
            posterPath,
            totalEpisodes,
            overview,
            seasonNumber
        )
}
