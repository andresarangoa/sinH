package com.example.logogenia.presentation.ui.objectsRecognition

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.logogenia.presentation.navigation.RouteNavigator
import com.example.logogenia.presentation.ui.home.HomeRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ObjectRecognitionViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator
): ViewModel(),RouteNavigator by routeNavigator {
    var detectionList = mutableStateOf<List<DetectionObject>>(listOf())
    var isLoading = mutableStateOf(value = false)

    fun setList(detectedObjectList: List<DetectionObject>){
        if (detectedObjectList.isNotEmpty()){
            isLoading.value = true
            detectionList.value = detectedObjectList
        }else{
            isLoading.value = false
        }
    }

    fun toHome(){
        routeNavigator.navigateToRoute(HomeRoute.get(0))
    }
    companion object{
        const val MODEL_FILE_NAME = "ssd_mobilenet_v1_1_metadata_1.tflite"
        const val LABEL_FILE_NAME = "dataset_labels_v2.txt"
    }
}