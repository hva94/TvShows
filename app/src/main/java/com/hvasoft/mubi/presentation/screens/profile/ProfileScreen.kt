package com.hvasoft.mubi.presentation.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val favorites by profileViewModel.favoritesTvShows.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        profileViewModel.getFavorites()
    }

    ProfileContent(
        onBack = onBack,
        favorites = favorites
    )
}