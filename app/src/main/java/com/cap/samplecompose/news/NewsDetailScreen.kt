package com.cap.samplecompose.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cap.samplecompose.helper.AppToolBar
import com.cap.samplecompose.helper.ShareClick
import com.cap.samplecompose.helper.Utils.modifier
import com.cap.samplecompose.helper.onBackClickListner
import com.cap.samplecompose.helper.onShareClickListner
import com.cap.samplecompose.model.ArticlesItem

@Composable
fun NewsDetailScreen(item: ArticlesItem?, navController: NavController) {
    val context = LocalContext.current
    AppToolBar(object : onBackClickListner {
        override fun onclicked() {
            navController.popBackStack()
        }
    }, object : onShareClickListner {
        override fun onclicked() {
            item?.url?.let { url ->
                context.ShareClick(url)
            }
        }
    })
    DetailScreen(item)
}

@Composable
fun DetailScreen(item: ArticlesItem?) {
    Row(modifier = Modifier.padding(vertical = 16.dp)) {
        Column {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item?.urlToImage),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .padding(16.dp, 0.dp, 16.dp, 16.dp)
                        .height(300.dp)
                        .fillMaxWidth()
                )
            }
            Text(
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 8.dp),
                text = item?.publishedAt ?: "",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
                text = item?.title ?: "",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
                text = item?.description ?: "",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
                text = item?.content ?: "",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
