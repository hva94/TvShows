package com.hvasoft.mubi.presentation.home

import androidx.paging.PagingData
import androidx.paging.map
import com.google.common.truth.Truth.assertThat
import com.hvasoft.mubi.CoroutineRule
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.model.TvShowFilter
import com.hvasoft.mubi.domain.use_case.TvShowsUseCases
import com.hvasoft.mubi.presentation.screens.home.HomeViewModel
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    private lateinit var useCases: TvShowsUseCases

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(
            useCases,
            UnconfinedTestDispatcher()
        )
    }

    @Test
    fun getTvShows() {
        assertThat(homeViewModel.tvShows.value).isInstanceOf(Flow::class.java)
    }

    @Test
    fun searchTvShows() = runTest {
        /** Given */
        every { useCases.getTvShows(tvShowFilter = TvShowFilter.TOP_RATED) } returns flowOf(
            PagingData.from(
                listOf(
                    TvShow(
                        backdropPath = "",
                        firstAirDate = "",
                        id = "1",
                        name = "TvShow name test",
                        originalLanguage = "",
                        originalName = "",
                        overview = "",
                        popularity = 0.0,
                        posterPath = "",
                        voteAverage = 0.0,
                        voteCount = 0,
                        isFavorite = false,
                        category = "",
                        seasons = listOf()
                    )
                )
            )
        )

        val results = arrayListOf<Flow<PagingData<TvShow>>>()
        val job = launch(UnconfinedTestDispatcher()) {
            homeViewModel.tvShows.toList(results)
        }

        /** When */
        homeViewModel.searchTvShows(tvShowFilter = TvShowFilter.TOP_RATED)

        /** Then */
        assertThat(results).isNotEmpty()
        results[1].first().map {
            assertThat(it).isNotNull()
            assertThat(it.name).isEqualTo("TvShow name test")
            assertThat(it.id).isEqualTo("1")
        }
        job.cancel()
    }
}