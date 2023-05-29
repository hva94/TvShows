package com.hvasoft.mubi.domain.use_case

import com.hvasoft.mubi.domain.repository.TvShowRepository
import javax.inject.Inject

class SetIsFavoriteUC @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    suspend operator fun invoke(isFavorite: Boolean, tvShowId: String) =
        tvShowRepository.setIsFavorite(isFavorite = isFavorite, tvShowId = tvShowId)
}