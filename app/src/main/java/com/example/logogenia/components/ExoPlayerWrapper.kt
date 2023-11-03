package com.example.logogenia.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logogenia.presentation.ui.theme.gerberaFontFamily


@Composable
fun Title(text: String) {
    Text(
        text = text,
        fontFamily = gerberaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp, 0.dp, 8.dp)

    )
}

@Composable
fun TopBarTitle(text: String) {
    Text(
        text = text,
        fontFamily = gerberaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        color = Color.White,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp,8.dp)

    )
}