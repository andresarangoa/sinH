package com.sinh.player.usecases


import com.example.domain.databasemanager.model.UseCaseWithoutParams
import com.old.domain.model.Either
import com.old.domain.model.Failure
import com.sinh.player.repository.IPlayer
import javax.inject.Inject

class PreparePlayerUseCase
@Inject constructor(private val playerRepository: IPlayer){
    fun run(){
        playerRepository.prepare()
    }

}