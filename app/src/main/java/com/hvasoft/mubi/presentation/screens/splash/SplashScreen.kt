package com.hvasoft.mubi.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.hvasoft.mubi.R
import com.hvasoft.mubi.presentation.theme.Magnolia
import com.hvasoft.mubi.presentation.theme.NeonBlue
import com.hvasoft.mubi.presentation.theme.PADDING_24
import com.hvasoft.mubi.presentation.theme.SummerSky
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToHome: () -> Unit
) {

    val gradient = Brush.verticalGradient(
        listOf(
            SummerSky,
            NeonBlue,
            NeonBlue,
            NeonBlue
        )
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = gradient
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_mubi),
            contentDescription = stringResource(
                id = R.string.logo_description
            ),
            modifier = Modifier.padding(end = PADDING_24)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.ExtraBold,
            color = Magnolia
        )
    }
    LaunchedEffect(key1 = true) {
        delay(1500L)
        navigateToHome()
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navigateToHome = {})
}