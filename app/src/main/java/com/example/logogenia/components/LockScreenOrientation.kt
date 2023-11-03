package com.example.logogenia.components

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

@Composable
fun LockScreenOrientation(orientation: Int) {
    val configuration = LocalConfiguration.current
    val activity = LocalContext.current as? Activity
    DisposableEffect(configuration.orientation) {
        activity?.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity?.requestedOrientation = orientation

        }
    }
}

@Composable
fun LockScreenOrientationDisposable(orientation: Int) {
    val configuration = LocalConfiguration.current
    val activity = LocalContext.current as? Activity
    val oldOrientation = configuration.orientation
    DisposableEffect(configuration.orientation) {
        activity?.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity?.requestedOrientation = oldOrientation

        }
    }
}