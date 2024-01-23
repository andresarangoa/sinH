package com.sinh.player.repository

import androidx.media3.common.Player
interface IPlayer {

    fun setVideoPlayer(uri : String)

    fun play()

    fun prepare()

    fun getPlayer() : Player
}