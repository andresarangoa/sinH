package com.sinh.player.model

import android.net.Uri
import androidx.media3.common.MediaItem

data class VideoItemPlayer(
    val content: Uri,
    val mediaItem: MediaItem,
    val name: String
)