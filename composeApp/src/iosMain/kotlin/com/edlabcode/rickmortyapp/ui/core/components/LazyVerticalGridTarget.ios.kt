package com.edlabcode.rickmortyapp.ui.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import com.edlabcode.rickmortyapp.ui.core.BackgroundPrimaryColor

@Composable
actual fun <T : Any> LazyVerticalGridTarget(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit,
    header: @Composable () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.background(BackgroundPrimaryColor)
    ) {
        item(span = { GridItemSpan(2) }) { header() }
        items(pagingItems.itemCount) { pos ->
            pagingItems[pos]?.let { item ->
                itemView(item)
            }
        }
    }
}