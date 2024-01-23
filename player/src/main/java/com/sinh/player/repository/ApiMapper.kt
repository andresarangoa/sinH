package com.sinh.player.repository

import android.net.Uri
import androidx.media3.common.MediaItem
import com.example.domain.databasemanager.model.VideoItem
import com.sinh.player.model.VideoItemPlayer

fun VideoItem.toVideo(): VideoItemPlayer =
    VideoItemPlayer(
        Uri.parse(this.url),
        MediaItem.fromUri(this.url),
        this.name
    )
