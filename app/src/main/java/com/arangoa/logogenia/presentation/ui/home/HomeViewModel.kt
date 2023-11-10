package com.arangoa.logogenia.presentation.ui.home


import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.arangoa.logogenia.R
import com.arangoa.logogenia.presentation.navigation.RouteNavigator
import com.arangoa.logogenia.presentation.ui.knowingWords.KnowingWordsRoute
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
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

    fun toKnowingWords(){
        Log.d("DIOS","uhmmm")
        routeNavigator.navigateToRoute(KnowingWordsRoute.get(0))
    }

    init {
        event()
    }

    fun event(){
        firebaseAnalytics.logEvent("TEST"){
            param("test", "test")
        }
    }
}

data class NavigationComponent(val image: Int, val title: String, val action: ()->Unit)