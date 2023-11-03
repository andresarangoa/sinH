package com.example.domain.databasemanager

import com.example.domain.databasemanager.localDatabaseEntities.WordEntity
import com.old.domain.model.Either
import com.old.domain.model.Failure

interface ILocalDataSource {
    fun getListWords(): List<WordEntity>
    fun getListWordsByLetter(letter : String): List<WordEntity>
    fun createWords(listWords: List<WordEntity>): Either<Failure, Long>
    fun getDetailWord(word: String):WordEntity
}