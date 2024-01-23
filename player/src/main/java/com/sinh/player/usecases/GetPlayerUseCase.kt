package com.sinh.player.usecases

import androidx.media3.common.Player
import com.example.domain.databasemanager.model.UseCaseWithoutParams
import com.old.domain.model.Either
import com.old.domain.model.Failure
import com.sinh.player.repository.IPlayer
import javax.inject.Inject

class GetPlayerUseCase
@Inject constructor(private val playerRepository: IPlayer) {

     fun run() : Player =
         playerRepository.getPlayer()


}