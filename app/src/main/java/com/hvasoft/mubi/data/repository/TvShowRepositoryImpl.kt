package com.hvasoft.mubi.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hvasoft.mubi.data.local_db.TvShowDatabase
import com.hvasoft.mubi.data.local_db.dao.TvShowDao
import com.hvasoft.mubi.data.remote_db.service.TvShowApi
import com.hvasoft.mubi.data.util.DataConstants.NETWORK_PAGE_SIZE
import com.hvasoft.mubi.data.util.mappers.TvShowMapper
import com.hvasoft.mubi.data.util.mappers.TvShowMapperEntity
import com.hvasoft.mubi.data.util.pagination.TvShowRemoteMediator
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.model.TvShowFilter
import com.hvasoft.mubi.domain.repository.TvShowRepository
import com.hvasoft.mubi.domain.common.Result
import com.hvasoft.mubi.domain.common.fold
import com.hvasoft.mubi.domain.common.makeSafeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class TvShowRepositoryImpl @Inject constructor(
    private val tvShowDao: TvShowDao,
    private val tvShowDatabase: TvShowDatabase,
    private val tvShowApi: TvShowApi,
    private val tvShowMapper: TvShowMapper,
    private val tvShowMapperEntity: TvShowMapperEntity,
) : TvShowRepository {

    override fun getTvShows(tvShowFilter: TvShowFilter): Flow<PagingData<TvShow>> {
        val tvShowSourceFactory = { tvShowDao.getTvShows(tvShowFilter.tvName) }
        return Pager(
            PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            null,
            TvShowRemoteMediator(
                tvShowDatabase = tvShowDatabase,
                tvShowApi = tvShowApi,
                tvShowFilter = tvShowFilter,
                tvShowMapper = tvShowMapper
            ),
            tvShowSourceFactory
        ).flow
            .map { page -> page.map { tvShowMapperEntity.map(it) } }
    }

    override suspend fun getDetailTvShowById(tvShowId: String): Result<TvShow> {
        val response = makeSafeRequest { tvShowApi.getDetailTvShowById(tvShowId) }
        return response.fold(
            onSuccess = {
                Result.Success(it.toDomain())
            },
            onError = { code, message ->
                Result.Error(code, message)
            },
            onException = {
                Result.Exception(it)
            }
        )
    }

    override suspend fun updateTvShow(tvShow: TvShow) {
        tvShowDao.updateTvShow(tvShowMapper.map(tvShow))
    }

    override suspend fun setIsFavorite(isFavorite: Boolean, tvShowId: String) {
        tvShowDao.setIsFavorite(tvShowId = tvShowId, isFavorite = isFavorite)
    }

    override suspend fun getFavoritesTvShows(): List<TvShow> {
        return tvShowDao.getFavoritesTvShows().map { tvShowMapperEntity.map(it) }
    }

}