package com.hvasoft.mubi.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hvasoft.mubi.presentation.components.ErrorScreen
import com.hvasoft.mubi.presentation.components.LoadingScreen
import com.hvasoft.mubi.presentation.theme.backgroundColor

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(
    tvShowId: String,
    category: String,
    isFavorite: Boolean,
    onBack: () -> Unit,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = tvShowId) {
        detailViewModel.getDetailTvShowById(tvShowId, category, isFavorite)
    }

    val detailState by detailViewModel.detailState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    if (detailState.isLoading) {
        LoadingScreen(
            modifier = Modifier.fillMaxSize()
        )
    } else {
        Scaffold { paddingValues ->
            detailState.error?.let {
                ErrorScreen(
                    modifier = Modifier.fillMaxSize(),
                    messageId = it.errorMessage,
                    onTryAgain = {
                        detailViewModel.getDetailTvShowById(
                            tvShowId,
                            category,
                            isFavorite
                        )
                    },
                    displayTryButton = it.displayTryAgainBtn
                )
            }
            detailState.tvShow?.let { tvShow ->
                DetailContent(
                    tvShow = tvShow,
                    scrollState = scrollState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                        .background(color = MaterialTheme.colorScheme.backgroundColor),
                    setAsFavorite = {
                        detailViewModel.setIsFavorite(
                            isFavorite = it,
                            tvShowId = tvShow.id
                        )
                    },
                    onBack = onBack
                )
            }
        }
    }
}