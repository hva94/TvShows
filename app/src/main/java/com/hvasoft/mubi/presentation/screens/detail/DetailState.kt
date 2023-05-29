package com.hvasoft.mubi.presentation.screens.detail

import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.presentation.util.error_handling.ErrorState

data class DetailState(
    val isLoading: Boolean = false,
    val tvShow: TvShow? = null,
    val error: ErrorState? = null
)