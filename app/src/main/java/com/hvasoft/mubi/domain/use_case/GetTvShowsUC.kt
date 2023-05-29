package com.hvasoft.mubi.domain.use_case

import androidx.paging.PagingData
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.model.TvShowFilter
import com.hvasoft.mubi.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTvShowsUC @Inject constructor(
    private val tvShowRepository: TvShowRepository,
) {
    operator fun invoke(tvShowFilter: TvShowFilter): Flow<PagingData<TvShow>> =
        tvShowRepository.getTvShows(tvShowFilter)
}