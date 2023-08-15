package com.example.logogenia.presentation.ui.knowingWords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.logogenia.presentation.navigation.KEY_CONTENT_PAGE_INDEX
import com.example.logogenia.presentation.navigation.NavRoute
import com.example.logogenia.presentation.navigation.getOrThrow
import com.example.logogenia.presentation.ui.theme.GrayLight
import com.example.logogenia.presentation.ui.theme.LogogeniaTheme

object KnowingWordsRoute : NavRoute<KnowingWordsViewModel> {

    override val route = "knowingWords/{$KEY_CONTENT_PAGE_INDEX}/"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(index: Int): String = route.replace("{$KEY_CONTENT_PAGE_INDEX}", "$index")

    fun getIndexFrom(savedStateHandle: SavedStateHandle) =
        savedStateHandle.getOrThrow<Int>(KEY_CONTENT_PAGE_INDEX)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(KEY_CONTENT_PAGE_INDEX) { type = NavType.IntType })


    @Composable
    override fun viewModel(): KnowingWordsViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: KnowingWordsViewModel) =
        ContentPage(viewModel)
}


@Composable
fun ContentPage(
    knowingWordsViewModel: KnowingWordsViewModel
) {
    LogogeniaTheme{
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(GrayLight)
                .padding(8.dp)

        ) {


        }
    }
}