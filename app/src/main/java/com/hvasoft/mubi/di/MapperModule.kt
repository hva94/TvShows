package com.hvasoft.mubi.di

import com.hvasoft.mubi.data.util.mappers.SeasonMapper
import com.hvasoft.mubi.data.util.mappers.SeasonMapperEntity
import com.hvasoft.mubi.data.util.mappers.TvShowMapper
import com.hvasoft.mubi.data.util.mappers.TvShowMapperEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun providesSeasonMapperEntity(): SeasonMapperEntity = SeasonMapperEntity()

    @Provides
    @Singleton
    fun providesSeasonMapper(): SeasonMapper = SeasonMapper()

    @Provides
    @Singleton
    fun providesTvShowMapper(
        seasonMapper: SeasonMapper
    ): TvShowMapper = TvShowMapper(seasonMapper)

    @Provides
    @Singleton
    fun providesTvShowMapperEntity(
        seasonMapperEntity: SeasonMapperEntity
    ): TvShowMapperEntity = TvShowMapperEntity(seasonMapperEntity)

}