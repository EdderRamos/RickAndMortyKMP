package com.edlabcode.rickmortyapp.di

import com.edlabcode.rickmortyapp.ui.datail.CharacterDetailViewModel
import com.edlabcode.rickmortyapp.ui.home.tabs.characters.CharactersViewModel
import com.edlabcode.rickmortyapp.ui.home.tabs.episodes.EpisodesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
    viewModelOf(::CharacterDetailViewModel)
}