package com.hvasoft.mubi.data.util.mappers

import com.hvasoft.mubi.domain.common.Mapper
import com.hvasoft.mubi.data.local_db.entities.SeasonEntity
import com.hvasoft.mubi.domain.model.Season
import javax.inject.Inject

class SeasonMapperEntity @Inject constructor(): Mapper<SeasonEntity, Season> {
    override fun map(input: SeasonEntity): Season {
        return with(input) {
            Season(id, name, posterPath, totalEpisodes, overview, seasonNumber)
        }
    }
}