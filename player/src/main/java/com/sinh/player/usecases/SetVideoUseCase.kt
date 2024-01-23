package com.sinh.player.usecases

import com.example.domain.databasemanager.model.UseCase
import com.old.domain.model.Either
import com.old.domain.model.Failure
import com.sinh.player.repository.IPlayer
import javax.inject.Inject

class SetVideoUseCase
@Inject constructor(private val playerRepository: IPlayer)  {
    data class Params(val uri: String)

      fun run(params: Params) {
         playerRepository.setVideoPlayer(params.uri)
    }

}