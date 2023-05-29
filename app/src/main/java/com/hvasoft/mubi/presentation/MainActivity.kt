package com.hvasoft.mubi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hvasoft.mubi.presentation.navigation.MubiNavigation
import com.hvasoft.mubi.presentation.theme.MubiTheme
import com.hvasoft.mubi.presentation.theme.statusBarColor
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberAnimatedNavController()
            val systemUiController = rememberSystemUiController()
            MubiTheme {
                systemUiController.setStatusBarColor(
                    color = MaterialTheme.colorScheme.statusBarColor
                )
                MubiNavigation(navController = navController)
            }
        }
    }

}