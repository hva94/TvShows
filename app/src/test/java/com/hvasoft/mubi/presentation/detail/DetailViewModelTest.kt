package com.hvasoft.mubi.presentation.detail

import com.google.common.truth.Truth.assertThat
import com.hvasoft.mubi.CoroutineRule
import com.hvasoft.mubi.domain.use_case.TvShowsUseCases
import com.hvasoft.mubi.presentation.screens.detail.DetailState
import com.hvasoft.mubi.presentation.screens.detail.DetailViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.runs
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @RelaxedMockK
    private lateinit var useCases: TvShowsUseCases

    private lateinit var detailViewModel: DetailViewModel

    private val slot = slot<String>()

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(
            useCases,
            UnconfinedTestDispatcher()
        )
    }

    @Test
    fun getDetailState() {
        assertThat(detailViewModel.detailState.value).isInstanceOf(DetailState::class.java)
    }

    @Test
    fun `getDetailState initial state`() {
        assertThat(detailViewModel.detailState.value).isEqualTo(DetailState())
        assertThat(detailViewModel.detailState.value.tvShow).isNull()
        assertThat(detailViewModel.detailState.value.error).isNull()
        assertThat(detailViewModel.detailState.value.isLoading).isFalse()
    }

    @Test
    fun setIsFavorite() = runTest {
        /** Given */
        coEvery { useCases.setIsFavorite(tvShowId = "1", isFavorite = true) } just runs

        /** When */
        detailViewModel.setIsFavorite(tvShowId = "1", isFavorite = true)

        /** Then */
        coVerify { useCases.setIsFavorite(tvShowId = "1", isFavorite = true) }
    }

}