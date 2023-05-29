package com.hvasoft.mubi.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hvasoft.mubi.di.IoDispatcher
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.model.TvShowFilter
import com.hvasoft.mubi.domain.use_case.TvShowsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: TvShowsUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _tvShows = MutableStateFlow(flowOf(PagingData.empty<TvShow>()))
    val tvShows = _tvShows.asStateFlow()

    fun searchTvShows(tvShowFilter: TvShowFilter) {
        _tvShows.value = useCases.getTvShows(tvShowFilter = tvShowFilter)
            .flowOn(dispatcher)
            .cachedIn(viewModelScope)
    }
}