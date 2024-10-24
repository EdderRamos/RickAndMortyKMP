package com.edlabcode.rickmortyapp.ui.core.components

import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems

@Composable
expect fun <T : Any> LazyRowTarget(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit
)