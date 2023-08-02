package com.cap.samplecompose.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cap.samplecompose.model.ArticlesItem

@Composable
fun NewsDetailScreen(item: ArticlesItem?, modifier: Modifier) {
    return Row {
        Column {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item?.urlToImage),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = modifier.padding(16.dp,0.dp,16.dp,0.dp)
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }
            Text(
                modifier =  modifier.padding(16.dp,0.dp,16.dp,0.dp),
                text = item?.title ?: "",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier =  modifier.padding(16.dp,0.dp,16.dp,0.dp),
                text = item?.description ?: "",
                style = MaterialTheme.typography.bodySmall
            )
            Divider(
                modifier =modifier.padding(16.dp,8.dp,16.dp,8.dp),
                color = Color.Blue,
                thickness = 1.dp
            )
        }
    }
}