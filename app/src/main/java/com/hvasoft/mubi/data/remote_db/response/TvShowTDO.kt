package com.hvasoft.mubi.data.remote_db.response

import com.hvasoft.mubi.domain.model.TvShow
import com.squareup.moshi.Json

data class TvShowTDO(
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "first_air_date")
    val firstAirDate: String,
    val id: Int,
    val name: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String? = null,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
    val seasons: List<SeasonTDO>? = null
) {
    fun toDomain(category: String = ""): TvShow = TvShow(
        backdropPath ?: "",
        firstAirDate,
        id.toString(),
        name,
        originalLanguage,
        originalName,
        overview,
        popularity,
        posterPath ?: "",
        voteAverage,
        voteCount,
        false,
        category,
        seasons?.map { it.toDomain() }.orEmpty()
    )
}