package com.example.logogenia.presentation.ui.knowingWords

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.logogenia.R
import com.example.logogenia.components.AlphabetCard
import com.example.logogenia.components.RoundedButtonIcon
import com.example.logogenia.components.TopBarTitle
import com.example.logogenia.presentation.navigation.KEY_CONTENT_PAGE_INDEX
import com.example.logogenia.presentation.navigation.NavRoute
import com.example.logogenia.presentation.navigation.getOrThrow
import com.example.logogenia.presentation.ui.alphabet
import com.example.logogenia.presentation.ui.getDrawableId
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentPage(
    knowingWordsViewModel: KnowingWordsViewModel
) {
    LogogeniaTheme{
        Scaffold(
            modifier = Modifier
                .padding(0.dp),
            topBar = {
                Row(modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .padding(0.dp)
                    .height(65.dp)){
                    Box(modifier = Modifier.fillMaxWidth(0.4f)){
                        RoundedButtonIcon(iconVector = R.drawable.ic_left, iconSize = 55.dp) {
                            knowingWordsViewModel.navigateUp()
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth(0.6f)){
                        TopBarTitle("Letras")
                    }
                }
            }
        ) { padding ->
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(GrayLight)
                    .padding(padding)

            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(3)){
                    items(alphabet){item ->
                        val drawableResourceId = getDrawableId(item, LocalContext.current)
                        val drawableSignResourceId = getDrawableId(item+"s", LocalContext.current)

                        AlphabetCard(drawableResourceId, drawableSignResourceId,item){
                            knowingWordsViewModel.toWordDetailScreen(item)
                        }

                    }
                }

            }
        }

    }


}