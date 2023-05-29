package com.hvasoft.mubi.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hvasoft.mubi.domain.model.TvShowFilter
import com.hvasoft.mubi.domain.model.getAllTvShowsTypes
import com.hvasoft.mubi.presentation.theme.COMMON_PADDING
import com.hvasoft.mubi.presentation.theme.Comet
import com.hvasoft.mubi.presentation.theme.LinkWater
import com.hvasoft.mubi.presentation.theme.Magnolia
import com.hvasoft.mubi.presentation.theme.PADDING_VERTICAL_CHIPS
import com.hvasoft.mubi.presentation.theme.backgroundColorChipSelected

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterChips(
    tvShowsType: List<TvShowFilter> = getAllTvShowsTypes(),
    selectedCTvShowFilter: TvShowFilter,
    onSelectionChange: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = COMMON_PADDING,
                top = PADDING_VERTICAL_CHIPS,
                bottom = PADDING_VERTICAL_CHIPS
            )
    ) {
        items(tvShowsType) {
            FilterChipItem(
                tvShowFilter = it,
                isSelected = selectedCTvShowFilter == it,
                onSelectionChange = onSelectionChange
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun FilterChipItem(
    tvShowFilter: TvShowFilter,
    isSelected: Boolean,
    onSelectionChange: (String) -> Unit
) {
    val color = if (isSelected) MaterialTheme.colorScheme.backgroundColorChipSelected else LinkWater
    Chip(
        onClick = { onSelectionChange(tvShowFilter.tvName) },
        modifier = Modifier.padding(horizontal = 4.dp),
        colors = ChipDefaults.chipColors(
            backgroundColor = color
        )
    ) {
        Text(
            text = stringResource(id = tvShowFilter.title),
            color = if (isSelected) Magnolia else Comet,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterChipsPreview() {
    FilterChips(
        tvShowsType = getAllTvShowsTypes(),
        selectedCTvShowFilter = TvShowFilter.TOP_RATED,
        onSelectionChange = {}
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FilterChipsPreviewDark() {
    FilterChips(
        tvShowsType = getAllTvShowsTypes(),
        selectedCTvShowFilter = TvShowFilter.TOP_RATED,
        onSelectionChange = {}
    )
}