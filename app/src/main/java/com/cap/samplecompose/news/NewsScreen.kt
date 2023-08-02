package com.cap.samplecompose.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cap.samplecompose.NewsDetailScreenRoute
import com.cap.samplecompose.helper.Utils
import com.cap.samplecompose.model.ArticlesItem
import com.cap.samplecompose.model.Resource
import com.cap.samplecompose.network.NetworkViewModel

@Composable
fun NewsScreen(viewModel: NetworkViewModel, navController: NavController) {
    when (val response = viewModel.newsListResponse.collectAsState().value) {
        is Resource.Loading -> {
            LoaderView()
        }

        is Resource.Error -> {
            ErrorScreen(
                modifier = Modifier.fillMaxWidth(), response.message
            )
        }

        is Resource.Success -> {
            NewsView(response.data, navController = navController,viewModel = viewModel)
        }
    }
}

@Composable
fun NewsView(article: List<ArticlesItem?>?, navController: NavController, viewModel: NetworkViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(count = article?.size ?: 0, itemContent = {
                NewsListItemView(item = article?.get(it), onNewsItemClick = {
                    viewModel.selectedArticle = it
                    navController.navigate(NewsDetailScreenRoute.route)
                })
            })
        }
    }
}

@Composable
fun NewsListItemView(
    item: ArticlesItem?, onNewsItemClick: (ArticlesItem?) -> Unit = {},
) {
    return Row {
        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {
                    onNewsItemClick(item)
                }) {
            Box(
                modifier = Utils.modifier,
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item?.urlToImage),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Utils.modifier
                        .padding(16.dp, 0.dp, 16.dp, 0.dp)
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }
            Text(
                modifier = Utils.modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
                text = item?.title ?: "",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Utils.modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
                text = item?.description ?: "",
                style = MaterialTheme.typography.bodySmall
            )
            Divider(
                modifier = Utils.modifier.padding(16.dp, 8.dp, 16.dp, 8.dp),
                color = Color.Blue,
                thickness = 1.dp
            )
        }
    }
}