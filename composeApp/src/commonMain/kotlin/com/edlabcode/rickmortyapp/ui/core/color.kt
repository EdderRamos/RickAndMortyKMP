package com.edlabcode.rickmortyapp.ui.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BackgroundPrimaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) primaryBlack else primaryWhite

val BackgroundSecondaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) secondaryBlack else secondaryWhite

val BackgroundTertiaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) tertiaryBlack else tertiaryWhite

val DefaultTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black


val Pink = Color(0xFFFF577D)
val Green = Color(0xFF5CCF92)

val primaryWhite = Color(0xFFFFFFFFF)
val secondaryWhite = Color(0xFFEAE8EF)
val tertiaryWhite = Color(0xFFFAFAFA)

val primaryBlack = Color(0xFF000000)
val secondaryBlack = Color(0xFF302F2F)
val tertiaryBlack = Color(0xFF464646)


