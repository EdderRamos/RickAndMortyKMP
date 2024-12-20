package com.edlabcode.rickmortyapp.ui.core.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.edlabcode.rickmortyapp.ui.core.components.PagingType.COLUMN
import com.edlabcode.rickmortyapp.ui.core.components.PagingType.ROW
import com.edlabcode.rickmortyapp.ui.core.components.PagingType.VERTICAL_GRID

enum class PagingType {
    ROW,
    COLUMN,
    VERTICAL_GRID,
}

@Composable
fun <T : Any> PagingWrapper(
    pagingType: PagingType,
    pagingItems: LazyPagingItems<T>,
    initialView: @Composable () -> Unit = {},
    emptyView: @Composable () -> Unit = {},
    extraItemsView: @Composable () -> Unit = {},
    itemView: @Composable (T) -> Unit = {},
    header: @Composable () -> Unit = {}
) {
    when {
        pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 -> {
            initialView()
        }

        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
            emptyView()
        }

        else -> {
            when (pagingType) {
                ROW -> {
                    LazyRowTarget(pagingItems, itemView)
                }

                COLUMN -> {
                    LazyColumn {
                        item { header() }
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let {
                                itemView(it)
                            }
                        }
                    }
                }

                VERTICAL_GRID -> {
                    LazyVerticalGridTarget(pagingItems, itemView, header)
                }
            }

            if (pagingItems.loadState.append is LoadState.Loading) {
                extraItemsView()
            }
        }
    }
}