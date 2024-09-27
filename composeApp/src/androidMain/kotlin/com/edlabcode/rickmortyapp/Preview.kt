package com.edlabcode.rickmortyapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import com.edlabcode.rickmortyapp.ui.home.tabs.characters.CharacterOfTheDay
import com.edlabcode.rickmortyapp.ui.home.tabs.characters.CharactersScreen


@Preview
@Composable
fun Preview(){
    CharacterOfTheDay(
        CharacterModel(1,
            image = "",
            isAlive = true,
            name = "PEPE"
        )
    )
}