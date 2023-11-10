package com.arangoa.logogenia.components

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arangoa.logogenia.R
import com.arangoa.logogenia.presentation.ui.theme.LogogeniaTheme
import com.arangoa.logogenia.presentation.ui.theme.gerberaFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlphabetCard(imageLetter: Int,imageSign: Int, text: String, onClick: () -> Unit){
   LogogeniaTheme(
   ) {
       Card( modifier = Modifier
           .width(150.dp)
           .height(180.dp)
           .padding(8.dp),
           onClick =  {
               onClick()
           }
       ) {
           Box( modifier = Modifier.fillMaxSize()){

               Image(
                   painter = painterForVersionAndroid(
                       imageRes = imageLetter
                   ),
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
                       fontFamily = gerberaFontFamily,
                       fontWeight = FontWeight.Bold,
                       color = Color.Black,
                       textAlign = TextAlign.Center,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(24.dp,8.dp,0.dp,8.dp)

                   )
               }

               Box (modifier = Modifier
                   .clip(RoundedCornerShape(20.dp))
                   .align(Alignment.BottomStart)
                   .width(50.dp)
                   .height(50.dp)
               ){
                   Image(
                       painter = painterForVersionAndroid(
                           imageRes = imageSign
                       ),
                       contentDescription = null,
                       contentScale = ContentScale.Crop,
                       modifier = Modifier
                           .fillMaxSize()
                           .background(MaterialTheme.colorScheme.primary)
                           .clip(RoundedCornerShape(20.dp))
                   )
               }
           }
       }
   }
}

@Preview
@Composable
private fun showAlphabetComponent(){
    AlphabetCard(R.drawable.word_presentation,R.drawable.word_presentation,"text"){}
}