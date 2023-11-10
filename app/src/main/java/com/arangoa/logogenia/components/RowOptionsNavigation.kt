package com.arangoa.logogenia.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arangoa.logogenia.presentation.ui.home.NavigationComponent
import com.arangoa.logogenia.presentation.ui.theme.gerberaFontFamily

@Composable
fun RowOptionsNavigation(listOfItems :  List<NavigationComponent>, title: String){
    Column {
        Text(  text = title,
            fontWeight = FontWeight.Bold,
            fontFamily = gerberaFontFamily,
            color = Color.Black,
            fontSize=20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))
        LazyRow() {
            items(listOfItems){ item ->
                EmptyCard(imageRes = item.image, text = item.title, item.action)
            }

        }
    }
}