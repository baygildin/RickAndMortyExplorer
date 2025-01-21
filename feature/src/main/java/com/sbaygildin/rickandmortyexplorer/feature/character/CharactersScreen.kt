package com.sbaygildin.rickandmortyexplorer.feature.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sbaygildin.rickandmortyexplorer.feature.R

@Composable
fun CharactersScreen(viewModel: CharactersViewModel, onCharacterClick: (Int) -> Unit) {

    val uiState = viewModel.uiState.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadCharacters(1)
    }
    if (uiState.isLoading && uiState.characters.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(120.dp),
                color = androidx.compose.ui.graphics.Color(0xffff6b00)
            )
        }
    } else if (uiState.error != null) {


        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Error: ${uiState.error}")
        }
    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, top = 90.dp, end = 12.dp, bottom = 24.dp)
        ) {
            if (uiState.characters.isNotEmpty()) Text(
                stringResource(R.string.characters),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.size(18.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.characters) { character ->
                    CharacterItem(
                        character = character,
                        onClick = { onCharacterClick(character.id) })
                }
                item {
                    if (!uiState.isLoading) {
                        LaunchedEffect(Unit) {
                            viewModel.loadNextPage()
                        }
                    }
                }

                if (uiState.isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = androidx.compose.ui.graphics.Color(
                                    0xffff6b00
                                )
                            )
                        }
                    }
                }
            }

        }
    }

}
