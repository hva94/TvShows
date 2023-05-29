package com.hvasoft.mubi.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hvasoft.mubi.presentation.theme.NeonBlue
import com.hvasoft.mubi.presentation.theme.PADDING_4

@Composable
fun ActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes buttonTextResId: Int
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(
            containerColor = NeonBlue
        )
    ) {
        Text(
            text = stringResource(id = buttonTextResId).uppercase(),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(PADDING_4),
            color = Color.White
        )
    }
}

@Composable
@Preview
fun ActionButtonPreview() {
    ActionButton(onClick = {}, buttonTextResId = 0)
}