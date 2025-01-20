package com.sbaygildin.rickandmortyexplorer.feature.character

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import coil3.request.ImageRequest
import coil3.request.crossfade


@Composable
fun CharacterItem(character: RMCharacter) {
    Text("Characters")
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        AsyncImage(
            model = character.image.trim(),
            contentDescription = character.name,
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_dialog_alert)
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Row {
                Text(character.name, style = MaterialTheme.typography.bodyLarge)
                Text(character.status, style = MaterialTheme.typography.bodySmall)
            }
            Text(text = character.species + ", " + character.gender, style = MaterialTheme.typography.bodySmall)
            Text(text = character.type, style = MaterialTheme.typography.bodySmall)
            Text(text = character.location, style = MaterialTheme.typography.bodySmall)
            Text(text = character.id.toString(), style = MaterialTheme.typography.bodySmall)
        }
    }
}