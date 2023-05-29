package com.hvasoft.mubi.presentation.util.error_handling

import com.hvasoft.mubi.domain.common.Error

data class ErrorLoadState(
    var error: Error? = null,
    var isAppend: Boolean = false,
    var isRefresh: Boolean = false
)