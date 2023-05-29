package com.hvasoft.mubi.data.util.mappers

import com.hvasoft.mubi.domain.common.Mapper
import com.hvasoft.mubi.data.local_db.entities.TvShowEntity
import com.hvasoft.mubi.domain.model.TvShow
import javax.inject.Inject

class TvShowMapperEntity @Inject constructor(
    private val seasonMapperEntity: SeasonMapperEntity,
) : Mapper<TvShowEntity, TvShow> {

    override fun map(input: TvShowEntity): TvShow {
        return with(input) {
            TvShow(
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
                seasons.map { seasonEntity -> seasonMapperEntity.map(seasonEntity) }
            )
        }
    }
}