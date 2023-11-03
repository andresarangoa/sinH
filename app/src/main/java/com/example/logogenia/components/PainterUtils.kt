package com.example.logogenia.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter

@Composable
fun painterForVersionAndroid(imageRes: Int): Painter = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
    painterResource(
        id = imageRes
    )
} else {
    rememberAsyncImagePainter(
        model = imageRes
    )
}
