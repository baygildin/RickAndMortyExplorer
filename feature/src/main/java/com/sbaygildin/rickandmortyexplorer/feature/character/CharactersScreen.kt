package com.sbaygildin.rickandmortyexplorer.feature.character

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import com.sbaygildin.rickandmortyexplorer.domain.repository.CharacterRepository
import com.sbaygildin.rickandmortyexplorer.domain.usecase.GetCharacterUseCase
import com.sbaygildin.rickandmortyexplorer.feature.R

@Composable
fun CharactersScreen(viewModel: CharactersViewModel) {

    val uiState = viewModel.uiState.collectAsState().value
    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.loadCharacters(1)
    }
    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(modifier = Modifier.size(120.dp))
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
            Text(stringResource(R.string.characters), style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.size(18.dp))
            LazyColumn() {
                items(uiState.characters) { character ->
                    CharacterItem(character = character)
                }
            }
        }


    }
}
