package com.example.logogenia.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.logogenia.presentation.ui.IBaseViewModel
import com.example.logogenia.presentation.ui.getDrawableId
import com.example.logogenia.presentation.ui.wordDetail.WordDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardHandler(
    imageRes: String?,
    text: String,
    baseViewModel: IBaseViewModel,
    icStatusPlayerButton: Int
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val request =
                ImageRequest.Builder(LocalContext.current).data(imageRes).allowHardware(false)
                    .build()
            Title(text = text)
            AsyncImage(
                model = request,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            )
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.BottomCenter)
            ) {
                val drawableRightArrow = getDrawableId("ic_left", LocalContext.current)
                val drawableLeftArrow = getDrawableId("ic_right", LocalContext.current)

                RoundedButtonIcon(drawableRightArrow, 50.dp) {
                    baseViewModel.postEvents(WordDetailViewModel.WordDetailsEvent.PreviousVideo)
                }
                RoundedButtonIcon(icStatusPlayerButton, 50.dp) {
                    baseViewModel.postEvents(WordDetailViewModel.WordDetailsEvent.PlayVideo)
                }
                RoundedButtonIcon(drawableLeftArrow, 50.dp) {
                    baseViewModel.postEvents(WordDetailViewModel.WordDetailsEvent.NextVideo)
                }

            }
        }
    }
}

@Composable
@Preview
fun ShowCardHandler() {

}
