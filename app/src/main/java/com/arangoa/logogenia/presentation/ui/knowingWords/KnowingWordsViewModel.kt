package com.arangoa.logogenia.presentation.ui.knowingWords

import androidx.lifecycle.ViewModel
import com.arangoa.logogenia.presentation.navigation.RouteNavigator
import com.arangoa.logogenia.presentation.ui.wordDetail.WordDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KnowingWordsViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator
): ViewModel(), RouteNavigator by routeNavigator {

     fun toWordDetailScreen(letter: String){
        navigateToRoute(WordDetailRoute.get(letter))
    }




}
