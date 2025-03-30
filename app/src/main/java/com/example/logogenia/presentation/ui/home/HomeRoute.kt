package com.example.logogenia.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.logogenia.R
import com.example.logogenia.components.RowOptionsNavigation
import com.example.logogenia.presentation.navigation.KEY_CONTENT_PAGE_INDEX
import com.example.logogenia.presentation.navigation.NavRoute
import com.example.logogenia.presentation.navigation.getOrThrow
import com.example.logogenia.presentation.ui.theme.GrayLight
import com.example.logogenia.presentation.ui.theme.LogogeniaTheme

object HomeRoute : NavRoute<HomeViewModel> {

    override val route = "home/{$KEY_CONTENT_PAGE_INDEX}/"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(index: Int): String =route.replace("{$KEY_CONTENT_PAGE_INDEX}", "$index")

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
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .padding(0.dp)
                        .height(40.dp),
                    title = {
                        Text("")
                    }
                )
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .fillMaxHeight(0.08f),
                    horizontalArrangement = Arrangement.Center,
                ) {

                    IconForBottomNav(Icons.Rounded.Home){

                    }
                    IconForBottomNav(R.drawable.ic_photo_camera){
                        homeViewModel.toObjectRecognition()
                    }

                }
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
                }
            }
        }
    }
}

@Composable
fun IconForBottomNav(resourceId: Int, onClick: ()-> Unit){
    IconButton(onClick = onClick) {
        Icon(painterResource(id = resourceId),
            contentDescription = "",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
        )
    }
}

@Composable
fun IconForBottomNav(resourceId: ImageVector, onClick: ()-> Unit){
    IconButton(onClick = onClick) {
        Icon(resourceId,
            contentDescription = "",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
        )
    }

}