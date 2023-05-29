package com.hvasoft.mubi.di

import androidx.paging.ExperimentalPagingApi
import com.hvasoft.mubi.data.local_db.TvShowDatabase
import com.hvasoft.mubi.data.local_db.dao.TvShowDao
import com.hvasoft.mubi.data.remote_db.service.TvShowApi
import com.hvasoft.mubi.data.repository.TvShowRepositoryImpl
import com.hvasoft.mubi.data.util.mappers.TvShowMapper
import com.hvasoft.mubi.data.util.mappers.TvShowMapperEntity
import com.hvasoft.mubi.domain.repository.TvShowRepository
import com.hvasoft.mubi.domain.use_case.GetDetailTvShowUC
import com.hvasoft.mubi.domain.use_case.GetFavoritesTvShowsUC
import com.hvasoft.mubi.domain.use_case.GetTvShowsUC
import com.hvasoft.mubi.domain.use_case.SetIsFavoriteUC
import com.hvasoft.mubi.domain.use_case.TvShowsUseCases
import com.hvasoft.mubi.domain.use_case.UpdateTvShowUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesTvRepository(
        tvShowDao: TvShowDao,
        tvShowDatabase: TvShowDatabase,
        tvApi: TvShowApi,
        tvShowMapper: TvShowMapper,
        tvShowMapperEntity: TvShowMapperEntity,
    ): TvShowRepository =
        TvShowRepositoryImpl(
            tvShowDao,
            tvShowDatabase,
            tvApi,
            tvShowMapper,
            tvShowMapperEntity
        )

    @Provides
    @Singleton
    fun providesTvShowsUseCases(
        tvShowRepository: TvShowRepository,
    ): TvShowsUseCases = TvShowsUseCases(
        getTvShows = GetTvShowsUC(tvShowRepository),
        getDetailTvShowById = GetDetailTvShowUC(tvShowRepository),
        setIsFavorite = SetIsFavoriteUC(tvShowRepository),
        updateTvShow = UpdateTvShowUC(tvShowRepository),
        getFavoritesTvShows = GetFavoritesTvShowsUC(tvShowRepository)
    )
}