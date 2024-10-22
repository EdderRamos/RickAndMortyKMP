package com.edlabcode.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import com.edlabcode.rickmortyapp.ui.core.ex.vertical
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.rickface

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(
    navigateToDetail: (CharacterModel) -> Unit
) {
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val state by charactersViewModel.state.collectAsState()

    val characters = state.characters.collectAsLazyPagingItems()

    CharactersGridList(characters, state.characterOfTheDay, onItemSelected = navigateToDetail)

}

@Composable
fun CharactersGridList(
    characters: LazyPagingItems<CharacterModel>,
    characterOfTheDay: CharacterModel?,
    onItemSelected: (CharacterModel) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(span = { GridItemSpan(2) }) { CharacterOfTheDay(characterOfTheDay) }

        when {
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(60.dp),
                            color = Color.Green
                        )
                    }
                }
            }

            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                item { Text("No hay personajes :(") }
            }

            else -> {
                items(characters.itemCount) { pos ->
                    characters[pos]?.let { characterModel ->
                        CharacterItemList(characterModel) { onItemSelected(characterModel) }
                    }
                }
                if (characters.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier.fillMaxHeight()
                                .height(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(62.dp),
                                color = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItemList(
    characterModel: CharacterModel,
    onItemSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24))
            .border(
                2.dp, color = Color.Green,
                shape = RoundedCornerShape(0, 24, 0, 24)
            ).fillMaxWidth()
            .height(150.dp)
            .clickable { onItemSelected() },
        contentAlignment = Alignment.BottomCenter,
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(Res.drawable.rickface)
        )
        Box(
            modifier = Modifier.fillMaxWidth().height(60.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Black.copy(0f),
                            Color.Black.copy(0.6f),
                            Color.Black
                        )
                    )
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                characterModel.name, color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun CharacterOfTheDay(characterModel: CharacterModel? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        shape = RoundedCornerShape(12)
    ) {
        if (characterModel == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Box(contentAlignment = Alignment.BottomStart) {
                AsyncImage(
                    model = characterModel.image,
                    contentDescription = "Character",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
                Box(
                    Modifier.fillMaxSize().background(
                        brush = Brush.horizontalGradient(
                            0f to Color.Black.copy(alpha = .9f),
                            0.4f to Color.White.copy(alpha = 0f)
                        )
                    )
                )

                Text(
                    text = characterModel.name,
                    fontSize = 40.sp,
                    maxLines = 1,
                    minLines = 1,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxHeight()
                        .vertical()
                        .rotate(-90f),
                    color = Color.White
                )
            }
        }
    }
}