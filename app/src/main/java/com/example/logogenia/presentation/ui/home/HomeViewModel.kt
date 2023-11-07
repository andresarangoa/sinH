package com.example.logogenia.presentation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.logogenia.R
import com.example.logogenia.presentation.navigation.RouteNavigator
import com.example.logogenia.presentation.ui.knowingWords.KnowingWordsRoute
import com.example.logogenia.presentation.ui.logEventFlow
import com.example.logogenia.presentation.ui.logTestEvent
import com.example.logogenia.presentation.ui.objectsRecognition.ObjectRecognitionRoute
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val firebaseAnalytics: FirebaseAnalytics
): ViewModel(), RouteNavigator by routeNavigator {


    val cards = listOf(
        NavigationComponent(R.drawable.word_presentation, "Te presento las palabras",
            { toKnowingWords() }),
        NavigationComponent(R.drawable.how_start_img, "¿Cómo empieza esta palabra?",
            { toKnowingWords() })
    )
    val cards2 = listOf(
        NavigationComponent(R.drawable.word_presentation, "Te presento las letras",
            { toKnowingWords() }),
        NavigationComponent(R.drawable.how_start_img, "¿Qué letra es?",
            { toKnowingWords() })
    )
    val cards3 = listOf(
        NavigationComponent(R.drawable.word_presentation, "Leer",
            { toKnowingWords() }),
        NavigationComponent(R.drawable.how_start_img, "Escribir",
            { toKnowingWords() })
    )

    fun sendAnalytics(){
        Log.d("tag","sent")
        logTestEvent(firebaseAnalytics)
    }

    fun toKnowingWords(){
        routeNavigator.navigateToRoute(KnowingWordsRoute.get(0))
    }

    fun toObjectRecognition(){
        routeNavigator.navigateToRoute(ObjectRecognitionRoute.get(0))
    }
}

data class NavigationComponent(val image: Int, val title: String, val action: ()->Unit)