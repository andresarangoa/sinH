package com.arangoa.domain.databasemanager.repository

import com.arangoa.domain.databasemanager.entities.ResultsEntity
import com.arangoa.domain.databasemanager.entities.WordEntityRemote
import com.arangoa.domain.databasemanager.localDatabaseEntities.WordEntity
import com.arangoa.domain.databasemanager.model.Word

fun List<WordEntity>.toWordList():List<Word> = this.map {
    it.run {
        Word(
          word, letter, image, video
        )
    }
}

fun List<Word>.toWordEntityList():List<WordEntity> = this.map {
    it.run {
        WordEntity(
          word, letter, image, video
        )
    }
}

fun List<WordEntityRemote>.toWordsList():List<Word> = this.map{
    it.run {
        Word(
            word, letter[0], image, video
        )
    }
}