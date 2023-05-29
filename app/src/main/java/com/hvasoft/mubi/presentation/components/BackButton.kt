package com.hvasoft.mubi.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hvasoft.mubi.R
import com.hvasoft.mubi.presentation.theme.Magnolia

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    IconButton(
        onClick = onBack,
        modifier = modifier
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.back),
            tint = Magnolia
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BackButtonPreview() {
    BackButton(onBack = {})
}