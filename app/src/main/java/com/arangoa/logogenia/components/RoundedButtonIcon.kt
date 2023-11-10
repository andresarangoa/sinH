package com.arangoa.logogenia.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  RoundedButtonIcon(iconVector : Int, iconSize: Dp, onClick: () -> Unit){
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .size(iconSize)
            .clip(RoundedCornerShape(iconSize/2))
            .fillMaxSize()
    ) {
        Image(
            painter = painterForVersionAndroid(imageRes = iconVector),
            contentDescription = ""
        )
    }
}