package com.arangoa.logogenia.presentation.ui.wordDetail

import androidx.lifecycle.SavedStateHandle
import androidx.media3.common.Player
import com.arangoa.domain.databasemanager.repository.MaterialRepository
import com.arangoa.domain.databasemanager.usecases.GetWordsByLetterUseCase
import com.arangoa.logogenia.presentation.navigation.LogogeniaRouteNavigator
import com.arangoa.logogenia.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import com.arangoa.logogenia.presentation.navigation.getOrThrow
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arangoa.domain.databasemanager.model.Word
import com.old.domain.model.Either
import org.amshove.kluent.internal.assertEquals
import org.amshove.kluent.shouldBeEqualTo

@ExperimentalCoroutinesApi
class WordDetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    private val letter: String= "a"
    private val params: GetWordsByLetterUseCase.Params = GetWordsByLetterUseCase.Params(letter)
    private val materialRepository: MaterialRepository = mock()


    private val player: Player = mock()
    private val getWordsByLetterUseCase: GetWordsByLetterUseCase by lazy {
        GetWordsByLetterUseCase(materialRepository)
    }

    private val routeNavigator: LogogeniaRouteNavigator by lazy {
       LogogeniaRouteNavigator()
    }

    private val savedState: SavedStateHandle = mock {
        on { get<String>("CONTENT_PAGE_INDEX") } doReturn letter
        on { getOrThrow<String>("CONTENT_PAGE_INDEX") } doReturn letter
    }

    private val wordDetailViewModel: WordDetailViewModel by lazy {
        WordDetailViewModel(
            savedState,
            routeNavigator,
            getWordsByLetterUseCase,
            player
        )
    }

    @Test
    fun `when view model is created then load letter value`() = runTest {
        // given
        whenever(
            getWordsByLetterUseCase.run(params)
        ).doAnswer() {
           Either.Right(listOf(Word.empty))
        }
        assertEquals(expected= letter, actual= wordDetailViewModel.letter.value,)
    }

    @Test
    fun `given an correct letter then all words by that letter should be retrieve and start by the letter`()= runTest{
        val mockListWord = listOf(Word("arandano",'a', "image",""))
        whenever(
            getWordsByLetterUseCase.run(params)
        ).thenReturn(Either.Right(mockListWord))
        // when
        wordDetailViewModel.allWords.observeForever {
            it.size shouldBeEqualTo 1
            it[0].letter shouldBeEqualTo mockListWord[0].letter
        }
    }
}