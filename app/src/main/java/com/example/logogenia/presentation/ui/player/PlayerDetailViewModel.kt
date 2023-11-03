package com.example.logogenia.presentation.ui.wordDetail

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
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
import com.old.domain.model.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
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

    private val videoUris = savedStateHandle.getStateFlow("videoUris", emptyList<Uri>())

    val mediaItems = arrayListOf<MediaItem>()
    val videoItems = videoUris.map { uris ->
        uris.map { uri ->
            VideoItem(
                content = uri,
                mediaItem = MediaItem.fromUri(uri),
                name =  "No name"
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun Uri.toVideoItem() = VideoItem(
        this,
        MediaItem.fromUri(this),
        "No name"
    )


    fun addVideoUr(uri: Uri) {
        savedStateHandle["videoUris"] = videoUris.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playVid(uri: Uri) {
        player.setMediaItem(
            videoItems.value.find { it.content == uri }?.mediaItem ?: return
        )
    }

    init {
        _letter.value = WordDetailRoute.getStringFrom(savedStateHandle)
        loadAllWordsData()
    }

    private fun loadAllWordsData() =
        getWordsUseCase(GetWordsUseCase.Params(1), viewModelScope) { it.fold(::handleFailure, ::handleTopRatedMovieList) }


    private fun handleTopRatedMovieList( words: List<Word>) {
        Log.d("INNVIEWM","${words?.size}")
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
        Log.d("FALLO","$failure")
        _failure.value = failure
    }

    fun playVideo(){
        var uri: Uri?= allWords.value!!.get(videoPosition.value?:0).video.toUri()
        player.addMediaItem(MediaItem.fromUri(uri!!))

        uri = Uri.parse("https://drive.google.com/file/d/1Zo8cmOYeV6SK4fTcY8eIAdr1zVRDIwPj/view?usp=drive_link")
        player.setMediaItem(
            uri.toVideoItem().mediaItem
        )
    }

    fun addVideoUri(uri: Uri){
        val uri: Uri?= videoPosition.value?.let { allWords.value?.get(it)?.video?.toUri()}
        uri?.let { MediaItem.fromUri(it) }?.let { player.addMediaItem(it) }
    }


    sealed class WordDetailsStatus {
        data class ShowWords(val listOfWords : List<Word>):WordDetailsStatus()
    }


}

data class VideoItem(
    val content: Uri,
    val mediaItem: MediaItem,
    val name: String
)
data class States<out T>(private val content:T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }
}