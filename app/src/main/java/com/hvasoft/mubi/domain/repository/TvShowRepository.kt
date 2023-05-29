package com.hvasoft.mubi.domain.repository

import androidx.paging.PagingData
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.model.TvShowFilter
import com.hvasoft.mubi.domain.common.Result
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {

    fun getTvShows(tvShowFilter: TvShowFilter): Flow<PagingData<TvShow>>
    suspend fun getDetailTvShowById(tvShowId: String): Result<TvShow>
    suspend fun updateTvShow(tvShow: TvShow)
    suspend fun setIsFavorite(isFavorite: Boolean, tvShowId: String)
    suspend fun getFavoritesTvShows(): List<TvShow>

}