package com.hvasoft.mubi.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hvasoft.mubi.R
import com.hvasoft.mubi.presentation.theme.COMMON_PADDING
import com.hvasoft.mubi.presentation.theme.ERROR_LOTTIE_SIZE
import com.hvasoft.mubi.presentation.theme.FONT_SIZE_ERROR_MESSAGE
import com.hvasoft.mubi.presentation.theme.backgroundColor
import com.hvasoft.mubi.presentation.theme.textColor
import com.hvasoft.mubi.domain.common.Error

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    @StringRes messageId: Int,
    onTryAgain: () -> Unit,
    displayTryButton: Boolean
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.backgroundColor)
            .padding(COMMON_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition,
            progress = { progress },
            modifier = Modifier
                .size(ERROR_LOTTIE_SIZE)
        )
        Text(
            text = stringResource(id = messageId),
            fontSize = FONT_SIZE_ERROR_MESSAGE,
            color = MaterialTheme.colorScheme.textColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        if (displayTryButton) {
            ActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = COMMON_PADDING),
                onClick = onTryAgain,
                buttonTextResId = R.string.try_again
            )
        }
    }
}

@Composable
fun handleError(error: Error): Int {
    return when (error) {
        Error.Connectivity -> R.string.error_connectivity
        Error.InternetConnection -> R.string.error_internet
        is Error.HttpException -> error.messageResId
        is Error.Unknown -> R.string.error_unknown
        is Error.NotFoundTvShow -> error.messageResId
    }
}

@Preview(heightDp = 600)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        messageId = R.string.error_unknown,
        onTryAgain = {},
        displayTryButton = true
    )
}

@Preview(heightDp = 600, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ErrorScreenPreviewDark() {
    ErrorScreen(
        messageId = R.string.error_unknown,
        onTryAgain = {},
        displayTryButton = true
    )
}