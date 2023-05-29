package com.hvasoft.mubi.domain.use_case

import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.repository.TvShowRepository
import com.hvasoft.mubi.domain.common.Result
import javax.inject.Inject

class GetDetailTvShowUC @Inject constructor(
    private val tvShowRepository: TvShowRepository,
) {
    suspend operator fun invoke(tvShowId: String): Result<TvShow> =
        tvShowRepository.getDetailTvShowById(tvShowId)

}