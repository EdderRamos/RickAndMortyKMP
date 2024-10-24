package com.edlabcode.rickmortyapp.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import com.edlabcode.rickmortyapp.ui.core.DefaultTextColor
import com.edlabcode.rickmortyapp.ui.core.Green
import com.edlabcode.rickmortyapp.ui.core.components.PagingLoading
import com.edlabcode.rickmortyapp.ui.core.components.PagingType
import com.edlabcode.rickmortyapp.ui.core.components.PagingWrapper
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
    PagingWrapper(
        pagingType = PagingType.VERTICAL_GRID,
        pagingItems = characters,
        initialView = { PagingLoading() },
        itemView = { data ->
            CharacterItemList(data) { onItemSelected(data) }
        },
        header = {
            Column {
                Text(
                    "Characters",
                    color = DefaultTextColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(6.dp))
                CharacterOfTheDay(characterOfTheDay)
            }
        }
    )
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
                2.dp, color = Green,
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