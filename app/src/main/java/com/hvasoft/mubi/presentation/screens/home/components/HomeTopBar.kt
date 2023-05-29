package com.hvasoft.mubi.presentation.screens.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hvasoft.mubi.R
import com.hvasoft.mubi.presentation.theme.DEFAULT_ELEVATION
import com.hvasoft.mubi.presentation.theme.Magnolia
import com.hvasoft.mubi.presentation.theme.backgroundColorTopBar

@Composable
fun HomeTopBar(
    onProfileClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home_title),
                color = Magnolia,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Search, contentDescription = null, tint = Magnolia)
            }
            IconButton(onClick = onProfileClick) {
                Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Magnolia)
            }
        },
        backgroundColor = MaterialTheme.colorScheme.backgroundColorTopBar,
        elevation = DEFAULT_ELEVATION
    )
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar(
        onProfileClick = {}
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeTopBarPreviewDark() {
    HomeTopBar(
        onProfileClick = {}
    )
}