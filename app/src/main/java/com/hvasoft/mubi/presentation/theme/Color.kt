package com.hvasoft.mubi.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Magnolia = Color(0xFFFBFAFE)
val SummerSky = Color(0xFF31B5FE)
val NeonBlue = Color(0xFF6243FF)
val ParisM = Color(0xFF271A66)
val MediumBlue = Color(0xFF0013CA)
val ChineseBlack = Color(0xFF121212)
val FloralWhite = Color(0xFFF5F3F0)
val Comet = Color(0xFF6B6B83)
val Nero = Color(0xFF2A2A2A)
val IrisBlue = Color(0xFF21BDCA)
val LinkWater = Color(0xFFD5D8DB)
val Manatee = Color(0xFF8C8CA1)

val ColorScheme.statusBarColor
    @Composable
    get() = if (!isSystemInDarkTheme()) MediumBlue else ChineseBlack

val ColorScheme.backgroundColor
    @Composable
    get() = if (!isSystemInDarkTheme()) FloralWhite else ChineseBlack

val ColorScheme.backgroundColorTopBar
    @Composable
    get() = if (!isSystemInDarkTheme()) NeonBlue else ParisM

val ColorScheme.textColor
    @Composable
    get() = if (!isSystemInDarkTheme()) Comet else Color.White

val ColorScheme.backgroundColorChipSelected
    @Composable
    get() = if (!isSystemInDarkTheme()) NeonBlue else ParisM

val ColorScheme.backgroundCardColor
    @Composable
    get() = if (!isSystemInDarkTheme()) FloralWhite else Nero