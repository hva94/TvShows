package com.hvasoft.mubi.domain.use_case

import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.repository.TvShowRepository
import javax.inject.Inject

class GetFavoritesTvShowsUC @Inject constructor(
    private val tvShowRepository: TvShowRepository,
) {
    suspend operator fun invoke(): List<TvShow> =
        tvShowRepository.getFavoritesTvShows()
}