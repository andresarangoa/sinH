package com.arangoa.logogenia.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.arangoa.logogenia.R
import com.arangoa.logogenia.components.RoundedButtonIcon
import com.arangoa.logogenia.components.RowOptionsNavigation
import com.arangoa.logogenia.presentation.navigation.KEY_CONTENT_PAGE_INDEX
import com.arangoa.logogenia.presentation.navigation.NavRoute
import com.arangoa.logogenia.presentation.navigation.getOrThrow
import com.arangoa.logogenia.presentation.ui.theme.GrayLight
import com.arangoa.logogenia.presentation.ui.theme.LogogeniaTheme

object HomeRoute : NavRoute<HomeViewModel> {

    override val route = "home/"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(index: Int): String = route.replace("{$KEY_CONTENT_PAGE_INDEX}", "$index")

    fun getIndexFrom(savedStateHandle: SavedStateHandle) =
        savedStateHandle.getOrThrow<Int>(KEY_CONTENT_PAGE_INDEX)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(KEY_CONTENT_PAGE_INDEX) { type = NavType.IntType })


    @Composable
    override fun viewModel(): HomeViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: HomeViewModel) =
        ContentPage(viewModel)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentPage(
    homeViewModel: HomeViewModel
) {
    LogogeniaTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .padding(0.dp)
                        .height(40.dp),
                    title = {
                        Text("")
                    }
                )
            }
        ) { padding ->
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .background(GrayLight)
                    .padding(padding)

            ) {

                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    RowOptionsNavigation(homeViewModel.cards, "Señas")
                    RowOptionsNavigation(homeViewModel.cards2, "Dactilológico")
                    RowOptionsNavigation(homeViewModel.cards3, "Leer y Escribir")
                    Button(onClick = { homeViewModel.event()}) {
                        Text(text ="Test")
                    }
                }
            }
        }
    }
}