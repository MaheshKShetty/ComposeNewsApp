package com.cap.samplecompose.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
fun NewsScreen(article: List<ArticlesItem?>?,modifier: Modifier) {

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(count = article?.size ?: 0, itemContent = {
                NewsListItemView(item = article?.get(it), modifier = modifier)
            })
        }
    }
}

@Composable
fun NewsListItemView(item: ArticlesItem?,modifier: Modifier) {
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