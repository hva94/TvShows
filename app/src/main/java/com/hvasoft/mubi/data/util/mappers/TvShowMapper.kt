package com.hvasoft.mubi.data.util.mappers

import com.hvasoft.mubi.domain.common.Mapper
import com.hvasoft.mubi.data.local_db.entities.TvShowEntity
import com.hvasoft.mubi.domain.model.TvShow
import javax.inject.Inject

class TvShowMapper @Inject constructor(
    private val seasonMapper: SeasonMapper
) : Mapper<TvShow, TvShowEntity> {

    override fun map(input: TvShow): TvShowEntity {
        return with(input) {
            TvShowEntity(
                backdropPath,
                firstAirDate,
                id,
                name,
                originalLanguage,
                originalName,
                overview,
                popularity,
                posterPath,
                voteAverage,
                voteCount,
                isFavorite,
                category,
                seasons.map { season -> seasonMapper.map(season) }
            )
        }
    }
}