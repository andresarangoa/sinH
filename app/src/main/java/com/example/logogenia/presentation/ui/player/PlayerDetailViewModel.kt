package com.example.logogenia.presentation.ui.wordDetail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import com.example.domain.databasemanager.model.Word
import com.example.domain.databasemanager.usecases.GetWordsUseCase
import com.example.logogenia.presentation.navigation.RouteNavigator
import com.example.logogenia.presentation.navigation.States
import com.old.domain.model.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
    private val getWordsUseCase: GetWordsUseCase,
    val player: Player
): ViewModel(),RouteNavigator by routeNavigator {

    private val _letter: MutableLiveData<String> = MutableLiveData()
    val letter: LiveData<String> = _letter

    private val _allWords: MutableLiveData<List<Word>> = MutableLiveData()
    val allWords: LiveData<List<Word>> = _allWords

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    private val _status: MutableLiveData<States<WordDetailsStatus>> = MutableLiveData()
    val status: LiveData<States<WordDetailsStatus>> = _status

    private val _videoPosition : MutableLiveData<Int> = MutableLiveData(0)
    val videoPosition: LiveData<Int> = _videoPosition

    val mediaItems = arrayListOf<MediaItem>()

    init {
        _letter.value = WordDetailRoute.getStringFrom(savedStateHandle)
        loadAllWordsData()
    }

    private fun loadAllWordsData() =
        getWordsUseCase(GetWordsUseCase.Params(1), viewModelScope) { it.fold(::handleFailure, ::handleMediaPlayerItem) }

    private fun handleMediaPlayerItem( words: List<Word>) {
        _allWords.value = words
        player.prepare()
        allWords.value?.forEach {
            mediaItems.add(
                MediaItem.Builder()
                    .setUri(it.video)
                    .setMimeType(MimeTypes.APPLICATION_MP4)
                    .build()
            )
        }
        player.addMediaItems(mediaItems)
        _status.value = States(WordDetailsStatus.ShowWords(words))
    }
    private fun handleFailure(failure: Failure) {
        _failure.value = failure
    }

    sealed class WordDetailsStatus {
        data class ShowWords(val listOfWords : List<Word>):WordDetailsStatus()
    }
}

