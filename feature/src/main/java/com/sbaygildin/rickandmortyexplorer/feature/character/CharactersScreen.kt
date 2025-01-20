package com.sbaygildin.rickandmortyexplorer.feature.character

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun CharactersScreen(viewModel: CharactersViewModel) {

    val uiState = viewModel.uiState.collectAsState().value
    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.loadCharacters(1)
    }
    Log.d("CharactersScreenLog", "${uiState.characters} uiState.characters")
    if (uiState.isLoading){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("uiState.isLoading")
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }
    } else if (uiState.error != null) {
        Text("Error: ${uiState.error}")
    } else {
        AsyncImage(
            model = "https://w0.peakpx.com/wallpaper/371/172/HD-wallpaper-gotham-dgfdg-dsfsf-thumbnail.jpg",
            contentDescription = "name",
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_dialog_alert)
        )

        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(uiState.characters) {character ->


                CharacterItem(character = character)
            }
        }
    }
}