package com.hvasoft.mubi.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hvasoft.mubi.di.IoDispatcher
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.use_case.TvShowsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: TvShowsUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _favoritesTvShows = MutableStateFlow<List<TvShow>>(emptyList())
    val favoritesTvShows = _favoritesTvShows.asStateFlow()

    fun getFavorites() {
        viewModelScope.launch(dispatcher) {
            _favoritesTvShows.value = useCases.getFavoritesTvShows()
        }
    }
}