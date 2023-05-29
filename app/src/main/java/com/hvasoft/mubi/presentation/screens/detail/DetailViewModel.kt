package com.hvasoft.mubi.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hvasoft.mubi.di.IoDispatcher
import com.hvasoft.mubi.domain.common.fold
import com.hvasoft.mubi.domain.common.toError
import com.hvasoft.mubi.domain.common.validateHttpErrorCode
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.use_case.TvShowsUseCases
import com.hvasoft.mubi.presentation.util.error_handling.ErrorStateResolver.handleError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: TvShowsUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    fun getDetailTvShowById(tvShowId: String, category: String, isFavorite: Boolean) {
        _detailState.update { it.copy(isLoading = true, tvShow = null, error = null) }
        viewModelScope.launch(dispatcher) {
            useCases.getDetailTvShowById(tvShowId).fold(
                onSuccess = { tvShow ->
                    tvShow.category = category
                    tvShow.isFavorite = isFavorite
                    withContext(dispatcher) { updateTvShow(tvShow) }
                    _detailState.update {
                        it.copy(
                            isLoading = false,
                            tvShow = tvShow,
                            error = null
                        )
                    }
                },
                onError = { code, _ ->
                    val error = handleError(code.validateHttpErrorCode())
                    _detailState.update {
                        it.copy(
                            isLoading = false,
                            tvShow = null,
                            error = error
                        )
                    }
                },
                onException = { exception ->
                    val error = handleError(exception.toError())
                    _detailState.update {
                        it.copy(
                            isLoading = false,
                            tvShow = null,
                            error = error
                        )
                    }
                }
            )
        }
    }

    fun setIsFavorite(tvShowId: String, isFavorite: Boolean) {
        viewModelScope.launch(dispatcher) {
            useCases.setIsFavorite(tvShowId = tvShowId, isFavorite = isFavorite)
        }
    }

    private fun updateTvShow(tvShow: TvShow) {
        viewModelScope.launch(dispatcher) {
            useCases.updateTvShow(tvShow)
        }
    }

}