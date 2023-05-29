package com.hvasoft.mubi.data.util.mappers

import com.hvasoft.mubi.domain.common.Mapper
import com.hvasoft.mubi.data.local_db.entities.SeasonEntity
import com.hvasoft.mubi.domain.model.Season
import javax.inject.Inject

class SeasonMapper @Inject constructor(): Mapper<Season, SeasonEntity> {
    override fun map(input: Season): SeasonEntity {
        return with(input) {
            SeasonEntity(id, name, posterPath, totalEpisodes, overview, seasonNumber)
        }
    }
}