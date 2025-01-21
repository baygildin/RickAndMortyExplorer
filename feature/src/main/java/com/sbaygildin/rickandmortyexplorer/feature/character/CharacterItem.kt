package com.sbaygildin.rickandmortyexplorer.feature.character


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.sbaygildin.rickandmortyexplorer.core.ui.SFUIText
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import com.sbaygildin.rickandmortyexplorer.feature.R


@Composable
fun CharacterItem(character: RMCharacter, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    ) {

        val matrix = ColorMatrix()
        matrix.setToSaturation(0f)

        Box(modifier = Modifier.clip(RoundedCornerShape(40.dp))) {
            AsyncImage(
                model = character.image.trim(),
                contentDescription = character.name,
                modifier = Modifier.size(120.dp),
                colorFilter = if (character.status == "Dead") ColorFilter.colorMatrix(matrix)
                else null,
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                error = painterResource(id = android.R.drawable.ic_dialog_alert)
            )
        }

        Column(modifier = Modifier.padding(start = 8.dp)) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                StatusBadge(status = character.status)
            }

            Text(
                text = "${character.species}, ${character.gender}",
                style = MaterialTheme.typography.bodySmall
            )
            WatchEpisodesBadge()
            Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp)) {
                Image(
                    painter = painterResource(id = com.sbaygildin.rickandmortyexplorer.core.R.drawable.baseline_location_on_24),
                    contentDescription = "Location Icon",
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = character.locationName,
                    style = MaterialTheme.typography.bodySmall,
                    color = androidx.compose.ui.graphics.Color( 0xff525252)
                )
            }
        }
    }
}

@Composable
fun StatusBadge(status: String) {
    val backgroundColor = when (status) {
        "Alive" -> androidx.compose.ui.graphics.Color(0xFFC7FFB9)
        "Dead" -> androidx.compose.ui.graphics.Color(0xFFFFE8E0)
        else -> androidx.compose.ui.graphics.Color(0xffeeeeee)
    }

    val textColor = when (status) {
        "Alive" -> androidx.compose.ui.graphics.Color(0xFF319F16)
        "Dead" -> androidx.compose.ui.graphics.Color(0xFFE93800)
        else -> androidx.compose.ui.graphics.Color(0xffa0a0a0)
    }
    val statusSize = when (status) {
        "Alive" -> 55
        "Dead" -> 56
        else -> 92
    }

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(width = statusSize.dp, height = 25.dp)
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(25.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = status.uppercase(),
            color = textColor,
            style = TextStyle(
                fontFamily = SFUIText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}


@Composable
fun WatchEpisodesBadge() {
    val backgroundColor = androidx.compose.ui.graphics.Color(0xffff6b00).copy(alpha = 0.1f)
    val textColor = androidx.compose.ui.graphics.Color(0xffff6b00)
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(width = 148.dp, height = 35.dp)
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(17.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {

        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp)) {
            Image(
                painter = painterResource(id = com.sbaygildin.rickandmortyexplorer.core.R.drawable.play_icon),
                contentDescription = "Play Icon",
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = stringResource(R.string.watch_episodes),
                color = textColor,
                style = TextStyle(
                    fontFamily = SFUIText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}