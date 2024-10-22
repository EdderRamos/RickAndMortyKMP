package com.edlabcode.rickmortyapp.ui.home.tabs.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.edlabcode.rickmortyapp.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EpisodesViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(EpisodesState())
    val state = _state.asStateFlow()

    init {
        _state.update { it.copy(episodes = repository.getAllEpisodes().cachedIn(viewModelScope)) }
    }
}