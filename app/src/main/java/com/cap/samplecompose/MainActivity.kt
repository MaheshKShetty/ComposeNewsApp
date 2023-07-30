package com.cap.samplecompose

import android.icu.text.ListFormatter.Width
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberAsyncImagePainter
import com.cap.samplecompose.model.ArticlesItem
import com.cap.samplecompose.network.NetworkViewModel
import com.cap.samplecompose.splash.SplashViewModel
import com.cap.samplecompose.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()
    private val networkViewModel: NetworkViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { viewModel.isLoading.value }
        networkViewModel.getNewList()
        setContent {
            MyApplicationTheme {
                NewListView(networkViewModel)
            }
        }
    }

    @Composable
    fun NewsListItemView(item: ArticlesItem?) {
        return Row {
            Column {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(item?.urlToImage),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp, 0.dp),
                    text = item?.title ?: "",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp, 4.dp),
                    text = item?.description ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
                Divider(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp, 4.dp),
                    color = Color.Blue,
                    thickness = 1.dp
                )
            }
        }
    }

    @Composable
    fun NewListView(vwModel: NetworkViewModel) {

        LaunchedEffect(Unit) {
            vwModel.getNewList()
        }

        val newsList: State<List<ArticlesItem?>?> = vwModel.newsListResponse.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(count = newsList.value?.size ?: 0, itemContent = {
                    NewsListItemView(item = newsList.value?.get(it))
                })
            }
        }
    }
}