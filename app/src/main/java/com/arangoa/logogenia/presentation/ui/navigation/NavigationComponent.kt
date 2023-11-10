package com.arangoa.logogenia.presentation.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.arangoa.logogenia.presentation.navigation.INavigationComponent
import com.arangoa.logogenia.presentation.ui.home.HomeRoute
import com.arangoa.logogenia.presentation.ui.knowingWords.KnowingWordsRoute
import com.arangoa.logogenia.presentation.ui.wordDetail.WordDetailRoute

class NavigationMainComponent: INavigationComponent {

    @Composable
    override fun navigationComponent(navHostController: NavHostController,
                                     paddingValues: PaddingValues,
                                     startDestination: String
    ) {
        NavHost(
            navController = navHostController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            HomeRoute.composable(this, navHostController)
            KnowingWordsRoute.composable(this, navHostController)
            WordDetailRoute.composable(this, navHostController)
        }
    }

}