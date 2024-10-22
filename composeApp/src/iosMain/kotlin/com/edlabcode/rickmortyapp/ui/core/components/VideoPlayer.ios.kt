package com.edlabcode.rickmortyapp.ui.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.UIKit.UIView

@Composable
actual fun VideoPlayer(modifier: Modifier, url: String) {
    val webView = remember { WkWebView() }

    UIKitView(
        modifier = modifier,
        factory = {
            val container = UIView().apply {
                autorizingMask = UIViewAutoresizingFLexibleWidth or UIViewAutoresizingFlexibleHeight

            }
            webView.apply {

            }
        }
    )
}