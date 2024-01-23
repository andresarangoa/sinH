package com.example.logogenia.presentation.ui.wordDetail

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.domain.databasemanager.model.Word
import com.example.domain.databasemanager.repository.MaterialRepository
import com.example.domain.databasemanager.usecases.GetWordsByLetterUseCase
import com.example.logogenia.presentation.navigation.LogogeniaRouteNavigator
import com.example.logogenia.presentation.navigation.getOrThrow
import com.example.logogenia.utils.MainCoroutineRule
import com.old.domain.model.Either
import com.sinh.player.repository.IPlayer
import com.sinh.player.usecases.GetPlayerUseCase
import com.sinh.player.usecases.PlayVideoUseCase
import com.sinh.player.usecases.PreparePlayerUseCase
import com.sinh.player.usecases.SetVideoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.internal.assertEquals
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class WordDetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    private val letter: String= "a"
    private val params: GetWordsByLetterUseCase.Params = GetWordsByLetterUseCase.Params(letter)
    private val materialRepository: MaterialRepository = mock()
    private val playerRepository: IPlayer = mock()
    var uri: Uri = mock()
    var mediaItem = MediaItem.fromUri(uri)

    private val player: Player = mock()

    private val getWordsByLetterUseCase: GetWordsByLetterUseCase by lazy {
        GetWordsByLetterUseCase(materialRepository)
    }

    private val preparePlayerUseCase: PreparePlayerUseCase by lazy {
        PreparePlayerUseCase(playerRepository)
    }

    private val playVideoUseCase: PlayVideoUseCase by lazy {
        PlayVideoUseCase(playerRepository)
    }

    private val setVideoUseCase: SetVideoUseCase by lazy {
        SetVideoUseCase(playerRepository)
    }

    private val getPlayerUseCase: GetPlayerUseCase by lazy {
        GetPlayerUseCase(playerRepository)
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
            playVideoUseCase,
            preparePlayerUseCase,
            setVideoUseCase,
            getPlayerUseCase
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

        whenever(
            getPlayerUseCase.run()
        ).doAnswer() {
            player
        }

        assertEquals(expected = letter, actual = wordDetailViewModel.letter.value)
    }

    @Test
    fun `given an correct letter then all words by that letter should be retrieve and start by the letter`()= runTest{
        val mockListWord = listOf(Word("arandano",'a', "image",""))
        whenever(
            getWordsByLetterUseCase.run(params)
        ).thenReturn(Either.Right(mockListWord))
        whenever(
            getPlayerUseCase.run()
        ).doAnswer() {
           player
        }
        // when
        wordDetailViewModel.allWords.observeForever {
            it.size shouldBeEqualTo 1
            it[0].letter shouldBeEqualTo mockListWord[0].letter
        }
    }
}