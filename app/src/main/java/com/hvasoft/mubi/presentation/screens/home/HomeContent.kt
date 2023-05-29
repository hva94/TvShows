package com.hvasoft.mubi.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.hvasoft.mubi.R
import com.hvasoft.mubi.presentation.util.error_handling.ErrorLoadState
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.domain.model.TvShowFilter
import com.hvasoft.mubi.presentation.components.FilterChips
import com.hvasoft.mubi.presentation.components.LoadingScreen
import com.hvasoft.mubi.presentation.components.TvShowItem
import com.hvasoft.mubi.presentation.theme.COMMON_PADDING
import com.hvasoft.mubi.presentation.theme.NeonBlue
import com.hvasoft.mubi.presentation.theme.backgroundColor
import com.hvasoft.mubi.presentation.theme.textColor
import com.hvasoft.mubi.presentation.util.constants.HomeConstants
import com.hvasoft.mubi.presentation.util.constants.HomeConstants.PADDING_ITEM
import com.hvasoft.mubi.presentation.util.constants.HomeConstants.SPACING_ITEM
import com.hvasoft.mubi.presentation.util.extension.items
import com.hvasoft.mubi.presentation.util.extension.rememberCustomLazyGridState

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    tvShows: LazyPagingItems<TvShow>,
    onClickItem: (tvShow: TvShow) -> Unit,
    errorState: ErrorLoadState,
    selectedTvShowFilter: TvShowFilter,
    onSelectionChange: (String) -> Unit,
    isLoading: Boolean
) {
    Column(modifier = modifier) {
        FilterChips(
            onSelectionChange = onSelectionChange,
            selectedCTvShowFilter = selectedTvShowFilter
        )
        if (isLoading) {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(HomeConstants.ITEM_CARD_WIDTH),
                contentPadding = PaddingValues(PADDING_ITEM),
                horizontalArrangement = Arrangement.spacedBy(SPACING_ITEM),
                verticalArrangement = Arrangement.spacedBy(SPACING_ITEM),
                state = tvShows.rememberCustomLazyGridState()
            ) {
                items(
                    items = tvShows,
                    key = { tvShow -> tvShow.name }
                ) { tvShow: TvShow? ->
                    tvShow?.let {
                        TvShowItem(it, onClickItem = onClickItem)
                    }
                }
                item(span = { GridItemSpan(2) }) {
                    if (errorState.isAppend) {
                        ErrorMoreRetry(
                            onRetry = { tvShows.retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorMoreRetry(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.backgroundColor),
    ) {
        Text(
            text = stringResource(id = R.string.error_fetching_data),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = COMMON_PADDING, end = COMMON_PADDING, top = COMMON_PADDING),
            color = MaterialTheme.colorScheme.textColor,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { onRetry() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(COMMON_PADDING),
            shape = RoundedCornerShape(6.dp),
            contentPadding = PaddingValues(3.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = NeonBlue
            )
        ) {
            Text(
                text = stringResource(id = R.string.try_again).uppercase(),
                color = Color.White,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorMoreRetryPreview() {
    ErrorMoreRetry(onRetry = {})
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ErrorMoreRetryPreviewDark() {
    ErrorMoreRetry(onRetry = {})
}