package com.example.logogenia.presentation.ui.wordDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.domain.databasemanager.model.Word
import com.example.domain.databasemanager.usecases.GetWordsByLetterUseCase
import com.example.logogenia.R
import com.example.logogenia.presentation.navigation.RouteNavigator
import com.example.logogenia.presentation.ui.BaseViewModel
import com.old.domain.model.Failure
import com.sinh.player.usecases.GetPlayerUseCase
import com.sinh.player.usecases.PlayVideoUseCase
import com.sinh.player.usecases.PreparePlayerUseCase
import com.sinh.player.usecases.SetVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
    private val getWordsByLetterUseCase: GetWordsByLetterUseCase,
    private val playVideoUseCase: PlayVideoUseCase,
    private val preparePlayerUseCase: PreparePlayerUseCase,
    private val setVideoUseCase: SetVideoUseCase,
    private val getPlayerUseCase: GetPlayerUseCase
) : BaseViewModel<WordDetailViewModel.WordDetailsEvent>(),
    RouteNavigator by routeNavigator {
    sealed class WordDetailsEvent {
        object NextVideo : WordDetailsEvent()
        object PreviousVideo : WordDetailsEvent()
        object PlayVideo : WordDetailsEvent()
    }

    private val _letter: MutableLiveData<String> = MutableLiveData()
    val letter: LiveData<String> = _letter

    private val _allWords: MutableLiveData<List<Word>> = MutableLiveData()
    val allWords: LiveData<List<Word>> = _allWords

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    private val _wordPosition: MutableLiveData<Int> = MutableLiveData(0)
    val wordPosition: LiveData<Int> = _wordPosition

    private val _word: MutableLiveData<Word> = MutableLiveData()
    val word: LiveData<Word> = _word

    private val _icStatusPlayer: MutableLiveData<Int> = MutableLiveData(R.drawable.ic_play)
    val icStatePlayer: LiveData<Int> = _icStatusPlayer

    private val _player: MutableLiveData<Player> = MutableLiveData()
    val player: LiveData<Player> = _player

    init {
        preparePlayerUseCase.run()
        _player.value = getPlayerUseCase.run()
        _letter.value = WordDetailRoute.getStringFrom(savedStateHandle)
        _letter.value?.let { letter ->
            loadAllWordsData(letter)
        }
        exoPlayerListener()
    }

    private fun exoPlayerListener() {
        player.value?.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                if (isPlaying) {
                    _icStatusPlayer.value = R.drawable.ic_pause
                } else {
                    _icStatusPlayer.value = R.drawable.ic_play
                }
            }
        })
    }

    private fun loadAllWordsData(letter: String) =
        getWordsByLetterUseCase(
            GetWordsByLetterUseCase.Params(letter),
            viewModelScope
        ) { it.fold(::handleFailure, ::handleWordsToSetOnVideoPlayer) }

    private fun setPlayer(player : Player) {
        _player.value = player
    }
    private fun handleWordsToSetOnVideoPlayer(words: List<Word>) {
        _allWords.value = words
        setVideoOnPlayer()
    }

    private fun setVideoOnPlayer() {
        _word.value = wordPosition.value?.let { allWords.value?.get(it) }
        allWords.value?.let {
            setVideoUseCase.run(SetVideoUseCase.Params(it[wordPosition.value ?: 0].video))
        }
    }

    private fun handleFailure(failure: Failure) {
        _failure.value = failure
    }

    override fun manageEvent(event: Any) {
        when (event) {
            is WordDetailsEvent.NextVideo -> {
                if (wordPosition.value?.plus(1) ?: 0 < allWords.value?.size!!) {
                    _wordPosition.value = wordPosition.value?.plus(1)
                    setVideoOnPlayer()
                }
            }

            is WordDetailsEvent.PreviousVideo -> {
                if (wordPosition.value?.minus(1) ?: 0 >= 0) {
                    _wordPosition.value = wordPosition.value?.minus(1)
                    setVideoOnPlayer()
                }
            }

            is WordDetailsEvent.PlayVideo -> {
                playVideoUseCase.run()
            }
        }
    }
}

