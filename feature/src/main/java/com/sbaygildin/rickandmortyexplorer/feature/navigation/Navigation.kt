package com.sbaygildin.rickandmortyexplorer.feature.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import com.sbaygildin.rickandmortyexplorer.feature.character.CharacterDetailScreen
import com.sbaygildin.rickandmortyexplorer.feature.character.CharactersScreen
import com.sbaygildin.rickandmortyexplorer.feature.character.CharactersViewModel


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "character") {

        composable("character") {
            CharactersScreen(
                viewModel = hiltViewModel(),
                onCharacterClick = { characterId ->
                    navController.navigate("characterDetail/$characterId")
                }
            )
        }

        composable("characterDetail/{characterId}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            val viewModel: CharactersViewModel = hiltViewModel()

            val characterState = remember { mutableStateOf<RMCharacter?>(null) }

            LaunchedEffect(characterId) {
                if (characterId != null) {
                    viewModel.getCharacterById(characterId) { character ->
                        characterState.value = character
                    }
                }
            }

            if (characterId == null) {
                Text("Invalid character ID", modifier = Modifier.padding(16.dp))
            } else {
                characterState.value?.let {
                    CharacterDetailScreen(character = it)
                } ?: Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(120.dp),
                        color = androidx.compose.ui.graphics.Color(0xffff6b00)
                    )
                }
            }
        }

    }
}

