package com.edlabcode.rickmortyapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.edlabcode.rickmortyapp.di.initKoin
import com.edlabcode.rickmortyapp.ui.core.BackgroundPrimaryColor
import com.edlabcode.rickmortyapp.ui.core.Green
import dev.datlag.kcef.KCEF
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.math.max

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Rick and Morty App"
    ) {
        var restartRequired by remember { mutableStateOf(false) }
        var downloading by remember { mutableStateOf(0F) }
        var initialized by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                KCEF.init(builder = {
                    installDir(File("kcef-bundle"))
                    progress {
                        onDownloading {
                            downloading = max(it, 0F)
                        }
                        onInitialized {
                            initialized = true
                        }
                    }
                    settings {
                        cachePath = File("cache").absolutePath
                    }
                },
                    onError = { it?.printStackTrace() },
                    onRestartRequired = {
                        restartRequired = true
                    }
                )
            }
        }
        if (restartRequired) {
            Text("Restart required.")
        } else {
            if (initialized) {
                initKoin()
                App()
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor),
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = Green)
                    Text("Downloading $downloading")
                }
            }
        }

        DisposableEffect(Unit) {
            onDispose { KCEF.disposeBlocking() }
        }
    }
}