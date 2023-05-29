package com.hvasoft.mubi.presentation.util.error_handling

import androidx.annotation.StringRes

data class ErrorState(
    val displayTryAgainBtn: Boolean = false,
    val notFoundError: Boolean = false,
    @StringRes val errorMessage: Int
)