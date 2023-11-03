package com.example.logogenia.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logogenia.R
import com.example.logogenia.presentation.ui.theme.gerberaFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyCard(imageRes: Int, text: String, onClick: () -> Unit){
    Card( modifier = Modifier
        .width(190.dp)
        .height(230.dp)
        .padding(8.dp),
        onClick =  {
            onClick()
        }
      ) {
        Box( modifier = Modifier.fillMaxSize()){
              Image(
                  painter = painterForVersionAndroid(imageRes),
                  contentDescription = null,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                      .fillMaxSize()
                      .clip(RoundedCornerShape(8.dp))
              )

            Box (modifier = Modifier
                .background(color = Color.White)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.BottomCenter)
            ){
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    fontFamily = gerberaFontFamily,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                )
            }
        }
    }
}


@Preview
@Composable
fun showComponent(){
    EmptyCard(R.drawable.word_presentation,"text"){}
}