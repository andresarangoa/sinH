package com.example.logogenia.presentation.ui.wordDetail


import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.logogenia.R
import com.example.logogenia.components.CardHandler
import com.example.logogenia.components.ExoPlayerComponent
import com.example.logogenia.components.LockScreenOrientationDisposable
import com.example.logogenia.components.RoundedButtonIcon
import com.example.logogenia.presentation.navigation.KEY_CONTENT_PAGE_INDEX
import com.example.logogenia.presentation.navigation.NavRoute
import com.example.logogenia.presentation.navigation.getOrThrow
import com.example.logogenia.presentation.ui.theme.LogogeniaTheme

object WordDetailRoute: NavRoute<WordDetailViewModel> {

    override val route = "wordDetail/{$KEY_CONTENT_PAGE_INDEX}"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(index: String): String = route.replace("{$KEY_CONTENT_PAGE_INDEX}", "$index")

    fun getStringFrom(savedStateHandle: SavedStateHandle) =
        savedStateHandle.getOrThrow<String>(KEY_CONTENT_PAGE_INDEX)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(KEY_CONTENT_PAGE_INDEX) { type = NavType.StringType })


    @Composable
    override fun viewModel(): WordDetailViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: WordDetailViewModel){
        ContentPage(viewModel)
    }

}

@Composable
fun ContentPage(
    wordDetailViewModel: WordDetailViewModel
) {
    val configuration = LocalConfiguration.current
    LockScreenOrientationDisposable(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    //view(wordDetailViewModel = wordDetailViewModel, screenWidth = configuration.screenWidthDp.dp)
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            ViewWordDetail(
                wordDetailViewModel = wordDetailViewModel,
                screenWidth = configuration.screenWidthDp.dp
            )
        }

        else -> {
            LockScreenOrientationDisposable(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewWordDetail(wordDetailViewModel: WordDetailViewModel, screenWidth: Dp) {
    LogogeniaTheme {
        Scaffold(
            modifier = Modifier
                .padding(0.dp),
            topBar = {
                SmallTopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.padding(0.dp),
                    title = {
                        Text("")
                    },
                    navigationIcon = {
                        RoundedButtonIcon(iconVector = R.drawable.ic_left, iconSize = 65.dp) {
                            wordDetailViewModel.navigateUp()
                        }
                    }
                )
            }
        ) { _->
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)

            ) {
                val wordChanged by wordDetailViewModel.word.observeAsState()
                val playerStateIcon by wordDetailViewModel.icStatePlayer.observeAsState()
                var lifecycle by remember {
                    mutableStateOf(Lifecycle.Event.ON_CREATE)
                }
                val lifecycleOwner = LocalLifecycleOwner.current

                val oneThirdScreenWidth = screenWidth - ((screenWidth / 3) + 30.dp)
                val exoPlayer = wordDetailViewModel.player.value

                DisposableEffect(lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        lifecycle = event
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.6f)
                        ) {
                            ExoPlayerComponent(lifecycle, oneThirdScreenWidth, exoPlayer)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .padding(horizontal = 8.dp)
                                .align(Alignment.CenterEnd)
                        ) {
                            wordChanged?.let {
                                CardHandler(
                                    imageRes = it.image,
                                    text = it.word,
                                    baseViewModel = wordDetailViewModel,
                                    icStatusPlayerButton = playerStateIcon ?: 0
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}



