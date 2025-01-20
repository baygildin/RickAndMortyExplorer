package com.sbaygildin.rickandmortyexplorer.feature.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sbaygildin.rickandmortyexplorer.feature.auth.AuthScreen
import com.sbaygildin.rickandmortyexplorer.feature.character.CharactersScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "character") {
        composable("auth") {
            AuthScreen(
                viewModel = hiltViewModel(),
                onLoginSuccess = { navController.navigate("annotation") }
            )
        }

        composable("character"){
            CharactersScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}


