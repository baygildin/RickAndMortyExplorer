package com.sbaygildin.rickandmortyexplorer.feature.character


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter

@Composable
fun CharacterDetailScreen(character: RMCharacter) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, top = 90.dp, end = 12.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val matrix = ColorMatrix().apply { setToSaturation(0f) }

        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier.size(300.dp),
            colorFilter = if (character.status == "Dead") ColorFilter.colorMatrix(matrix) else null,
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_dialog_alert)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = character.name, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Status: ${character.status}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Species: ${character.species}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Gender: ${character.gender}", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "Location: ${character.locationName}",
            style = MaterialTheme.typography.bodyMedium
        )

    }
}
