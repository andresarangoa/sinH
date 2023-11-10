package com.arangoa.logogenia.components

import android.content.Context
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.arangoa.logogenia.R

@Composable
fun VideoPlayer(player: Player?, width: Dp, lifecycle: Lifecycle.Event) {

    AndroidView(
        factory = {
            PlayerView(it).also {
                it.player = player
            }
        },
        modifier = androidx.compose.ui.Modifier
            .width(width)
            .aspectRatio(16 / 9f),
        update = {
            when (lifecycle) {
                Lifecycle.Event.ON_PAUSE -> {
                    it.onPause()
                    it.player?.pause()
                }

                Lifecycle.Event.ON_RESUME -> {
                    it.onResume()
                }

                else -> Unit
            }
        },
    )
}

@Composable
fun ExoPlayerComponent(
    lifecycle: Lifecycle.Event,
    oneThirdScreenWidth: Dp,
    exoPlayer: Player?
) {

    DisposableEffect(
        AndroidView(
            factory = { context ->
                PlayerView(
                    context
                ).also {
                    it.player = exoPlayer
                    it.useController = false
                    it.background = ContextCompat.getDrawable(context, R.drawable.square_background)
                }
            },
            modifier = Modifier
                .width(oneThirdScreenWidth)
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp)),
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                    }

                    else -> Unit
                }
            },
        )
    ) {
        onDispose { exoPlayer?.stop() }
    }
}