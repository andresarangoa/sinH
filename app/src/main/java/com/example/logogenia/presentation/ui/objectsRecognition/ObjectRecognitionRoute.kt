package com.example.logogenia.presentation.ui.objectsRecognition

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.logogenia.R
import com.example.logogenia.components.CameraPermissions
import com.example.logogenia.components.CameraPreview
import com.example.logogenia.presentation.navigation.KEY_CONTENT_PAGE_INDEX
import com.example.logogenia.presentation.navigation.NavRoute
import com.example.logogenia.presentation.navigation.getOrThrow
import com.example.logogenia.presentation.ui.home.IconForBottomNav
import com.example.logogenia.presentation.ui.loadLabelsModel
import com.example.logogenia.presentation.ui.loadModel
import com.example.logogenia.presentation.ui.theme.GrayLight
import com.example.logogenia.presentation.ui.theme.LogogeniaTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.tensorflow.lite.Interpreter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object ObjectRecognitionRoute : NavRoute<ObjectRecognitionViewModel> {

    override val route = "objectRecognition/{$KEY_CONTENT_PAGE_INDEX}"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(index: Int): String = route.replace("{$KEY_CONTENT_PAGE_INDEX}", "$index")

    fun getStringFrom(savedStateHandle: SavedStateHandle) =
        savedStateHandle.getOrThrow<String>(KEY_CONTENT_PAGE_INDEX)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(KEY_CONTENT_PAGE_INDEX) { type = NavType.StringType })


    @Composable
    override fun viewModel(): ObjectRecognitionViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ObjectRecognitionViewModel) =
        ContentPage(viewModel)
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ContentPage(
    objectRecognitionViewModel: ObjectRecognitionViewModel
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
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .fillMaxHeight(0.08f),
                    horizontalArrangement = Arrangement.Center,
                ) {

                    IconForBottomNav(Icons.Rounded.Home) {
                        objectRecognitionViewModel.toHome()
                    }
                    IconForBottomNav(R.drawable.ic_photo_camera) {

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
                    .padding(8.dp)

            ) {
                val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
                val cameraExecutor = Executors.newSingleThreadExecutor()
                if (cameraPermissionState.status.isGranted) {
                    val assetManager = LocalContext.current.assets
                    val byteBuffer = loadModel(assets = assetManager)
                    val labels = loadLabelsModel(assets = assetManager)
                    val yubtorgbConverter = YuvToRgbConverter(LocalContext.current)
                    byteBuffer?.let {
                        OpenCamera(
                            cameraExecutor,
                            interpreter = Interpreter(it),
                            yubtorgbConverter,
                            labels,
                            objectRecognitionViewModel
                        )
                    }

                } else {
                    CameraPermissions(cameraPermissionState)
                }

            }
        }
    }
}

@Composable
fun OpenCamera(
    cameraExecutor: ExecutorService,
    interpreter: Interpreter,
    yubtorgbConverter : YuvToRgbConverter,
    labels: List<String>,
    viewModel: ObjectRecognitionViewModel
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    Column(modifier = Modifier.background(Color.Black)) {
        CameraPreview(
            context = context,
            lifecycleOwner = lifecycleOwner,
            cameraExecutor = cameraExecutor,
            interpreter = interpreter,
            yubtorgbConverter = yubtorgbConverter,
            labels = labels,
            viewModel = viewModel
        )
    }
}

