package com.edlabcode.rickmortyapp.ui.core.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.edlabcode.rickmortyapp.ui.core.DefaultTextColor

@Composable
actual fun VideoPlayer(modifier: Modifier, url: String) {
    Text("Not available for Desktop", modifier = modifier,
        color = DefaultTextColor)
}