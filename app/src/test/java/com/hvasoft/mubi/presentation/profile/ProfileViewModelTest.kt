package com.hvasoft.mubi.presentation.profile

import com.google.common.truth.Truth.assertThat
import com.hvasoft.mubi.CoroutineRule
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.use_case.TvShowsUseCases
import com.hvasoft.mubi.presentation.screens.profile.ProfileViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProfileViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    private lateinit var useCases: TvShowsUseCases

    private lateinit var profileViewModel: ProfileViewModel

    @Before
    fun setUp() {
        profileViewModel = ProfileViewModel(
            useCases,
            UnconfinedTestDispatcher()
        )
    }

    @Test
    fun getFavoritesTvShows() {
        assertThat(profileViewModel.favoritesTvShows.value).isEmpty()
    }

    @Test
    fun getFavorites() = runTest {
        /** Given */
        coEvery { useCases.getFavoritesTvShows() } returns listOf(
            TvShow(
                backdropPath = "",
                firstAirDate = "",
                id = "",
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
        val results = arrayListOf<List<TvShow>>()

        val job = launch(UnconfinedTestDispatcher()) {
            profileViewModel.favoritesTvShows.toList(results)
        }

        /** When */
        profileViewModel.getFavorites()

        /** Then */
        assertThat(results).isNotEmpty()
        assertThat(results[1]).isNotEmpty()
        assertThat(results[1].first().name).isEqualTo("TvShow name test")
        job.cancel()
    }

    @Test
    fun `getFavorites should return a empty list`() = runTest {
        /** Given */
        coEvery { useCases.getFavoritesTvShows() } returns listOf()
        val results = arrayListOf<List<TvShow>>()

        val job = launch(UnconfinedTestDispatcher()) {
            profileViewModel.favoritesTvShows.toList(results)
        }

        /** When */
        profileViewModel.getFavorites()

        /** Then */
        assertThat(results.first()).isEmpty()
        job.cancel()
    }
}