package com.arangoa.domain.databasemanager.usecases

import com.arangoa.domain.databasemanager.model.UseCase
import com.arangoa.domain.databasemanager.model.Word
import com.arangoa.domain.databasemanager.repository.MaterialRepository
import com.old.domain.model.Either
import com.old.domain.model.Failure
import javax.inject.Inject

class GetWordsUseCase
@Inject constructor(private val materialRepository: MaterialRepository) : UseCase<List<Word>, GetWordsUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Word>> = materialRepository.getAllWords()

    data class Params(val test: Int)
}
