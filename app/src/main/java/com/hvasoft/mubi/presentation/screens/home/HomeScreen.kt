package com.hvasoft.mubi.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hvasoft.mubi.presentation.util.error_handling.ErrorLoadState
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.model.TvShowFilter
import com.hvasoft.mubi.domain.model.getTvShowType
import com.hvasoft.mubi.presentation.components.ErrorScreen
import com.hvasoft.mubi.presentation.components.handleError
import com.hvasoft.mubi.presentation.screens.home.components.HomeTopBar
import com.hvasoft.mubi.presentation.theme.backgroundColor
import com.hvasoft.mubi.domain.common.toError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClickItem: (tvShow: TvShow) -> Unit,
    navigateToProfile: () -> Unit,
) {
    val selectedTvShowFilter = rememberSaveable { mutableStateOf(TvShowFilter.TOP_RATED) }

    LaunchedEffect(key1 = selectedTvShowFilter.value) {
        homeViewModel.searchTvShows(tvShowFilter = selectedTvShowFilter.value)
    }

    val tvShows =
        homeViewModel.tvShows.collectAsStateWithLifecycle().value.collectAsLazyPagingItems()
    val result = handlePagingResult(tvShows = tvShows)

    Scaffold(
        topBar = {
            HomeTopBar(onProfileClick = navigateToProfile)
        }
    ) { paddingValues ->
        if (result.isRefresh) {
            result.error?.let {
                ErrorScreen(
                    messageId = handleError(error = it),
                    modifier = Modifier.fillMaxSize(),
                    onTryAgain = { tvShows.retry() },
                    displayTryButton = true
                )
            }
        } else {
            HomeContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
                    .background(MaterialTheme.colorScheme.backgroundColor),
                tvShows = tvShows,
                onClickItem = onClickItem,
                errorState = result,
                isLoading = tvShows.loadState.refresh is LoadState.Loading,
                selectedTvShowFilter = selectedTvShowFilter.value,
                onSelectionChange = { tvShowFilterName ->
                    getTvShowType(tvShowFilterName)?.let { tvShowFilter ->
                        selectedTvShowFilter.value = tvShowFilter
                    }
                }
            )
        }
    }
}

@Composable
fun handlePagingResult(
    tvShows: LazyPagingItems<TvShow>,
): ErrorLoadState {
    val errorLoadState = ErrorLoadState()
    tvShows.apply {
        val stateError = when {
            loadState.refresh is LoadState.Error -> {
                errorLoadState.isRefresh = true
                loadState.refresh as LoadState.Error
            }

            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> {
                errorLoadState.isAppend = true
                loadState.append as LoadState.Error
            }

            else -> null
        }
        val error = stateError?.error?.toError()
        errorLoadState.error = error
        return errorLoadState
    }
}