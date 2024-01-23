package com.sinh.player.repository

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.old.domain.model.Either
import com.old.domain.model.Failure
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class IPlayerImp
@Inject constructor (private val player: Player) : IPlayer {
    override fun setVideoPlayer(uri : String) {
        player.setMediaItem(
            MediaItem.fromUri(uri))

    }

    override fun play()  {
        with(player) {
            if (this?.isPlaying == true) {
                this?.pause()
            } else if (this?.isPlaying == false && this?.getPlaybackState() == androidx.media3.common.Player.STATE_ENDED) {
                this?.seekTo(0)
                this?.playWhenReady = true
            } else {
                this?.playWhenReady = true
            }
        }
    }

    override fun prepare(){
        player.prepare()
    }

    override fun getPlayer() : Player= player

}