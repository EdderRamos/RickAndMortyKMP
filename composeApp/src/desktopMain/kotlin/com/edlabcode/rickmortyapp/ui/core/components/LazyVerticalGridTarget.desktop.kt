package com.edlabcode.rickmortyapp.ui.core.components

import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.edlabcode.rickmortyapp.ui.core.BackgroundPrimaryColor
import com.edlabcode.rickmortyapp.ui.core.Green

@Composable
actual fun <T : Any> LazyVerticalGridTarget(
    pagingItems: app.cash.paging.compose.LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    header: @Composable () -> Unit
) {
    val state = rememberLazyGridState()
    Box(contentAlignment = Alignment.TopEnd) {
        LazyVerticalGrid(
            state = state, columns = GridCells.Adaptive(180.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.background(BackgroundPrimaryColor)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) { header() }
            items(pagingItems.itemCount) { pos ->
                pagingItems[pos]?.let { item ->
                    itemView(item)
                }
            }
        }
        VerticalScrollbar(
            adapter = rememberScrollbarAdapter(state),
            style = LocalScrollbarStyle.current.copy(
                unhoverColor = Green.copy(.4f),
                hoverColor = Green,
                thickness = 10.dp
            )
        )

    }
}
