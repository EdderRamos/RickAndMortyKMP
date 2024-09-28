package com.edlabcode.rickmortyapp

import androidx.compose.ui.window.ComposeUIViewController
import com.edlabcode.rickmortyapp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }
