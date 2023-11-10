package com.arangoa.domain.databasemanager.localDatabaseEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey @ColumnInfo(name = "word")  val word: String,
    @ColumnInfo(name = "letter") val letter: Char,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "video") val video: String)