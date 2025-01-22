package com.sbaygildin.rickandmortyexplorer.feature.character

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.sbaygildin.rickandmortyexplorer.feature.R


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel,
    onCharacterClick: (Int) -> Unit,
) {

    val lazyPagingItems = viewModel.pagedCharactersFlow.collectAsLazyPagingItems()

    val loadState = lazyPagingItems.loadState

    if (loadState.refresh is LoadState.Loading) {
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
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(start = 12.dp, top = 90.dp, end = 12.dp, bottom = 24.dp)
        ) {
            item {
                Text(
                    stringResource(R.string.characters),
                    style = MaterialTheme.typography.headlineLarge,
                )
                Spacer(Modifier.padding(bottom = 18.dp))
            }
            items(lazyPagingItems.itemCount) { index ->
                lazyPagingItems[index]?.let { character ->
                    CharacterItem(
                        character = character,
                        onClick = { onCharacterClick(character.id) }
                    )
                }
            }
            item {
                if (loadState.append is LoadState.Loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        CircularProgressIndicator(
                            color = androidx.compose.ui.graphics.Color(
                                0xffff6b00
                            )
                        )
                    }
                }
            }
        }
        val errorState = when {
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            else -> null
        }
        errorState?.let { e ->
            Log.e("Paging", "Error = ${e.error}")
        }
    }
}
