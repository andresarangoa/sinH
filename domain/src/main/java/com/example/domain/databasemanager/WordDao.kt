package com.example.domain.databasemanager

import androidx.room.*
import com.example.domain.databasemanager.localDatabaseEntities.WordEntity


@Dao
interface WordDao {

    @Query("SELECT * FROM words")
    fun getAllWords(): List<WordEntity>

    @Query("SELECT * FROM words where letter = :letter")
    fun getAllWordsByLetter(letter: String): List<WordEntity>

    @Query("SELECT * FROM words WHERE word = :word")
    fun getWordDetail(word: String): WordEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(wordEntity: WordEntity): Long


    @Delete
    fun deleteCounter(wordEntity: WordEntity): Int

    @Query("DELETE FROM words")
    fun nukeTable(): Int
}