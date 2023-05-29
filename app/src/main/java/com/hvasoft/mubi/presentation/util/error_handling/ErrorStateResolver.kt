package com.hvasoft.mubi.presentation.util.error_handling

import com.hvasoft.mubi.R
import com.hvasoft.mubi.domain.common.Error

object ErrorStateResolver{

    fun handleError(error: Error): ErrorState {
        return when (error) {
            Error.Connectivity -> ErrorState(
                errorMessage = R.string.error_connectivity,
                displayTryAgainBtn = true
            )

            is Error.HttpException -> ErrorState(
                errorMessage = error.messageResId,
                displayTryAgainBtn = true
            )

            is Error.Unknown -> ErrorState(
                errorMessage = R.string.error_unknown,
                displayTryAgainBtn = true
            )

            Error.InternetConnection -> ErrorState(
                errorMessage = R.string.error_internet,
                displayTryAgainBtn = true
            )

            is Error.NotFoundTvShow -> ErrorState(
                errorMessage = error.messageResId,
                displayTryAgainBtn = false,
                notFoundError = true
            )
        }
    }

}