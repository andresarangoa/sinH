package com.example.data

import android.util.Log
import com.example.data.extensions.request
import com.example.data.platform.NetworkHandler
import com.example.domain.databasemanager.ILocalDataSource
import com.example.domain.databasemanager.WordDao
import com.example.domain.databasemanager.entities.ResultsEntity
import com.example.domain.databasemanager.entities.WordEntityRemote
import com.example.domain.databasemanager.localDatabaseEntities.WordEntity

import com.example.domain.databasemanager.model.Word
import com.example.domain.databasemanager.repository.MaterialApi
import com.example.domain.databasemanager.repository.MaterialRepository
import com.example.domain.databasemanager.repository.toWordEntityList
import com.example.domain.databasemanager.repository.toWordList
import com.example.domain.databasemanager.repository.toWordsList
import com.old.domain.model.Either
import com.old.domain.model.Failure
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RemoteDataSource
@Inject constructor(retrofit: Retrofit) : MaterialApi {
    private val wordsApi by lazy { retrofit.create(MaterialApi::class.java) }
    override fun words(): Call<List<WordEntityRemote>> = wordsApi.words()
    override fun wordsByLetter(letter: String): Call<List<WordEntityRemote>> = wordsApi.wordsByLetter(letter)
}

@Singleton
class LocalRepositoryDataSourceImp @Inject constructor(private val wordDao: WordDao): ILocalDataSource {
    override fun getListWords(): List<WordEntity> {
        return  wordDao.getAllWords()
    }

    override fun getListWordsByLetter(letter: String): List<WordEntity> {
        return  wordDao.getAllWordsByLetter(letter)
    }


    override fun createWords(listWords: List<WordEntity>): Either<Failure, Long> {
        var success = 0L

        listWords.forEach{
            wordDao.insertWord(it)
        }
        return try {
            when (success) {
                0L -> Either.Right(success)
                1L -> Either.Left(Failure.ServerError)
                else -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }


    }

    override fun getDetailWord(word: String): WordEntity {
        return wordDao.getWordDetail(word)
    }
}


@Suppress("KotlinConstantConditions")
class RepositoryImp
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val remoteDataSource: RemoteDataSource,
    private val localMaterialRepository: LocalRepositoryDataSourceImp
) : MaterialRepository {
    override fun wordsByLetter(letter: String): Either<Failure, List<Word>> {
        val request: Either<Failure, List<Word>> = when (networkHandler.isNetworkAvailable()) {
            true -> {
                request(
                    remoteDataSource.wordsByLetter(letter),
                    { it.toWordsList()},
                    emptyList()
                )
            }
            false -> getLocalWords(letter)
        }

        request.fold(
            fnL = {

            },
            fnR = {}
        )
        return request
    }

    override fun getAllWords(): Either<Failure, List<Word>> {
        val request: Either<Failure, List<Word>> = when (networkHandler.isNetworkAvailable()) {
            true -> {
            request(
                remoteDataSource.words(),
                { it.toWordsList()},
                emptyList()
            )
        }
            false -> getLocalWords("a")
        }

        request.fold(
            fnL = {

            },
            fnR = {}
        )
        return request
    }

    override fun initialSetup(listWords: List<Word>): Either<Failure, Long> {
        return localMaterialRepository.createWords(listWords.toWordEntityList())
    }

    private fun getLocalWords(letter: String): Either<Failure, List<Word>> {

        return try {
            val response = localMaterialRepository.getListWordsByLetter(letter)
            when (response.isNotEmpty()) {
                true -> Either.Right(response.toWordList())
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }

}