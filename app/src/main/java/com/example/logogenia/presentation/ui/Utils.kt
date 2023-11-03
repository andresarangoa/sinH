package com.example.logogenia.presentation.ui

import android.content.Context
<<<<<<< Updated upstream
=======
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.logogenia.presentation.ui.objectsRecognition.ObjectRecognitionViewModel
import com.example.logogenia.presentation.ui.objectsRecognition.ObjectRecognitionViewModel.Companion.LABEL_FILE_NAME
import com.example.logogenia.presentation.ui.objectsRecognition.ObjectRecognitionViewModel.Companion.MODEL_FILE_NAME
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
>>>>>>> Stashed changes

val alphabet = listOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")

fun getDrawableId(resourceName : String, context : Context) = context.resources.getIdentifier(
<<<<<<< Updated upstream
    resourceName, "drawable", context.packageName)
=======
    resourceName, "drawable", context.packageName)

abstract class BaseViewModel<in ViewEvent>   : ViewModel(), IBaseViewModel {
    override fun postEvents(event: Any) {
        manageEvent(event)
    }
    protected abstract fun manageEvent(event: Any)
}

@Composable
fun <T : Any, L : LiveData<T>> LifecycleOwner.observeCompose(liveData: L, body: (T?) -> Unit) =
    liveData.observeAsState()

interface IBaseViewModel{
    fun postEvents(event: Any)
}

fun logTestEvent(firebaseAnalytics: FirebaseAnalytics){
    val parameters = Bundle().apply {
        this.putString("level_name", "Caverns01")
        this.putInt("level_difficulty", 4)
    }

  firebaseAnalytics.logEvent("again_and_again",parameters)
}
fun logEventFlow( firebaseAnalytics: FirebaseAnalytics) = flow {
    val parameters = Bundle().apply {
        this.putString("level_name", "Caverns01")
        this.putInt("level_difficulty", 4)
    }

    emit( firebaseAnalytics.logEvent("again_and_again",parameters))
}.flowOn(Dispatchers.Default)

fun loadModel(fileName: String = MODEL_FILE_NAME, assets: AssetManager): ByteBuffer? {
    var modelBuffer: ByteBuffer? = null
    var file: AssetFileDescriptor? = null
    try {
        file = assets.openFd(fileName)
        val inputStream = FileInputStream(file.fileDescriptor)
        val fileChannel = inputStream.channel
        modelBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, file.startOffset, file.declaredLength)
    } catch (e: Exception) {
        Log.d( "Error loading model", "Error loading model ${e.message}")
    } finally {
        file?.close()
    }
    return modelBuffer
}

 fun loadLabelsModel(fileName: String = LABEL_FILE_NAME, assets: AssetManager): List<String> {
    var labels = listOf<String>()
    var inputStream: InputStream? = null
    try {
        inputStream = assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        labels = reader.readLines()
    } catch (e: Exception) {
        Log.d( "Error loading model", "Error loading model ${e.message}")
    } finally {
        inputStream?.close()
    }
    return labels
}

fun String.capitalizeFirstLetter(): String {
    return if (isNotEmpty()) {
        this[0].uppercaseChar() + substring(1)
    } else {
        this
    }
}
>>>>>>> Stashed changes
