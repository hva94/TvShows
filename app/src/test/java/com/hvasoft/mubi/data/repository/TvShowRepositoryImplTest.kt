package com.hvasoft.mubi.data.repository

import androidx.paging.ExperimentalPagingApi
import com.google.common.truth.Truth.assertThat
import com.hvasoft.mubi.CoroutineRule
import com.hvasoft.mubi.data.local_db.TvShowDatabase
import com.hvasoft.mubi.data.local_db.dao.TvShowDao
import com.hvasoft.mubi.data.local_db.entities.SeasonEntity
import com.hvasoft.mubi.data.local_db.entities.TvShowEntity
import com.hvasoft.mubi.data.remote_db.response.SeasonTDO
import com.hvasoft.mubi.data.remote_db.response.TvShowTDO
import com.hvasoft.mubi.data.remote_db.service.TvShowApi
import com.hvasoft.mubi.data.util.mappers.SeasonMapper
import com.hvasoft.mubi.data.util.mappers.SeasonMapperEntity
import com.hvasoft.mubi.data.util.mappers.TvShowMapper
import com.hvasoft.mubi.data.util.mappers.TvShowMapperEntity
import com.hvasoft.mubi.domain.model.Season
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.repository.TvShowRepository
import com.hvasoft.mubi.domain.common.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import io.mockk.verifyAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class TvShowRepositoryImplTest {

    private val season = Season(
        id = 1,
        name = "Season test",
        posterPath = null,
        totalEpisodes = null,
        overview = "Test overview",
        seasonNumber = 1
    )

    private val seasonEntity = SeasonEntity(
        id = 1,
        name = "Season test",
        posterPath = null,
        totalEpisodes = null,
        overview = "Test overview",
        seasonNumber = 1
    )

    private val seasonTDO = SeasonTDO(
        id = 1,
        name = "Season test",
        posterPath = null,
        totalEpisodes = null,
        overview = "Test overview",
        seasonNumber = 1
    )

    private val tvShow = TvShow(
        backdropPath = "",
        firstAirDate = "",
        id = "1",
        name = "TvShow name",
        originalLanguage = "",
        originalName = "",
        overview = "Overview TvShow",
        popularity = 4.5,
        posterPath = "",
        voteAverage = 3.0,
        voteCount = 8,
        isFavorite = false,
        category = "Top Rated",
        seasons = listOf(season)
    )

    private val tvShowEntity = TvShowEntity(
        backdropPath = "",
        firstAirDate = "",
        id = "1",
        name = "TvShow name",
        originalLanguage = "",
        originalName = "",
        overview = "Overview TvShow",
        popularity = 4.5,
        posterPath = "",
        voteAverage = 3.0,
        voteCount = 8,
        isFavorite = false,
        category = "Top Rated",
        seasons = listOf(seasonEntity)
    )

    private val tvShowTDO = TvShowTDO(
        backdropPath = "",
        firstAirDate = "",
        id = 1,
        name = "TvShow name",
        originalLanguage = "",
        originalName = "",
        overview = "Overview TvShow",
        popularity = 4.5,
        posterPath = "",
        voteAverage = 3.0,
        voteCount = 8,
        seasons = listOf(seasonTDO)
    )

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    private lateinit var tvShowDao: TvShowDao

    @RelaxedMockK
    private lateinit var tvShowDatabase: TvShowDatabase

    @RelaxedMockK
    private lateinit var tvShowApi: TvShowApi

    private val seasonMapper = SeasonMapper()

    private val tvShowMapper = TvShowMapper(seasonMapper)

    private val seasonMapperEntity = SeasonMapperEntity()

    private val tvShowMapperEntity = TvShowMapperEntity(seasonMapperEntity)

    private val slot = slot<String>()

    private lateinit var tvRepository: TvShowRepository

    @Before
    fun setUp() {
        tvRepository = TvShowRepositoryImpl(
            tvShowDao, tvShowDatabase, tvShowApi, tvShowMapper, tvShowMapperEntity
        )
    }

    @Test
    fun `getDetailTvShowById should be successful`() = runTest {
        /** Given */
        val response = mockk<Response<TvShowTDO>>()
        every { response.isSuccessful } returns true
        every { response.body() } returns tvShowTDO
        coEvery { tvShowApi.getDetailTvShowById(capture(slot)) } returns response

        /** When */
        val result = tvRepository.getDetailTvShowById("1")

        /** Then */
        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat((result as Result.Success).data).isNotNull()
        assertThat((result).data.name).isEqualTo("TvShow name")
        assertThat((result).data.popularity).isEqualTo(4.5)
        assertThat(slot.captured).isEqualTo("1")
        verifyAll {
            response.body()
            response.isSuccessful
        }
        coVerify { tvShowApi.getDetailTvShowById("1") }
    }

    @Test
    fun `getDetailTvShowById should return a result error`() = runTest {
        /** Given */
        val response = mockk<Response<TvShowTDO>>()
        every { response.isSuccessful } returns false
        every { response.body() } returns null
        every { response.code() } returns 404
        every { response.message() } returns "error"
        coEvery { tvShowApi.getDetailTvShowById(capture(slot)) } returns response

        /** When */
        val result = tvRepository.getDetailTvShowById("1")

        /** Then */
        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).code).isEqualTo(404)
        assertThat((result).message).isEqualTo("error")
        assertThat(slot.captured).isEqualTo("1")
        verifyAll {
            response.body()
            response.isSuccessful
            response.code()
            response.message()
        }
        coVerify { tvShowApi.getDetailTvShowById("1") }
    }

    @Test
    fun `getDetailTvShowById should return a result exception`() = runTest {
        /** Given */
        val exception = UnknownHostException("error")
        coEvery { tvShowApi.getDetailTvShowById(capture(slot)) } throws exception

        /** When */
        val result = tvRepository.getDetailTvShowById("1")

        /** Then */
        assertThat(result).isInstanceOf(Result.Exception::class.java)
        assertThat((result as Result.Exception).exception.message).isEqualTo("error")
        assertThat(slot.captured).isEqualTo("1")
        coVerify { tvShowApi.getDetailTvShowById("1") }
    }

    @Test
    fun setIsFavorite() = runTest {
        /** Given */
        coEvery { tvShowDao.setIsFavorite("1", true) } just runs

        /** When */
        tvRepository.setIsFavorite(true, "1")

        /** Then */
        coVerify { tvShowDao.setIsFavorite("1", true) }
    }

    @Test
    fun updateTvShow() = runTest {
        /** Given */
        coEvery { tvShowDao.updateTvShow(tvShowEntity) } just runs

        /** When */
        tvRepository.updateTvShow(tvShow)

        /** Then */
        coVerify { tvShowDao.updateTvShow(tvShowEntity) }
    }

    @Test
    fun `getFavoritesTvShows should return a list of tv shows`() = runTest {
        /** Given */
        coEvery { tvShowDao.getFavoritesTvShows() } returns listOf(tvShowEntity)

        /** When */
        val result = tvRepository.getFavoritesTvShows()

        /** Then */
        assertThat(result).isNotEmpty()
        assertThat(result).isNotNull()
        assertThat(result.count()).isEqualTo(1)
        assertThat(result.first().name).isEqualTo("TvShow name")
        assertThat(result.first().seasons.count()).isEqualTo(1)
        assertThat(result.first().seasons).isNotEmpty()
        assertThat(result.first().seasons.first().name).isEqualTo("Season test")
        coVerify { tvShowDao.getFavoritesTvShows() }
    }

    @Test
    fun `getFavoritesTvShows should return a empty list of tv shows`() = runTest {
        /** Given */
        coEvery { tvShowDao.getFavoritesTvShows() } returns listOf()

        /** When */
        val result = tvRepository.getFavoritesTvShows()

        /** Then */
        assertThat(result).isEmpty()
        coVerify { tvShowDao.getFavoritesTvShows() }
    }

}