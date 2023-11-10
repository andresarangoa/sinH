package com.arangoa.domain.databasemanager.usecases

import com.arangoa.domain.databasemanager.model.UseCase
import com.arangoa.domain.databasemanager.model.Word
import com.arangoa.domain.databasemanager.repository.MaterialRepository
import com.old.domain.model.Either
import com.old.domain.model.Failure
import javax.inject.Inject

class GetWordsByLetterUseCase
@Inject constructor(private val materialRepository: MaterialRepository) : UseCase<List<Word>, GetWordsByLetterUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Word>> = materialRepository.wordsByLetter(params.letter)

    data class Params(val letter: String)
}
