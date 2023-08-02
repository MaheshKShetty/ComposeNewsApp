package com.cap.samplecompose

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cap.samplecompose.model.Resource
import com.cap.samplecompose.network.NetworkViewModel
import com.cap.samplecompose.news.NewsScreen
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
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                when (val response = networkViewModel.newsListResponse.collectAsState().value) {
                    is Resource.Loading -> {
                        CustomCircularProgressBar()
                    }

                    is Resource.Error -> {
                        ErrorUi(
                            modifier = Modifier
                                .fillMaxWidth(), response.message
                        )
                    }

                    is Resource.Success -> {
                        val article = response.data
                        NewsScreen(
                            article = article,
                            modifier = Modifier
                                .fillMaxWidth(),
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun CustomCircularProgressBar() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = Color.Blue,
                strokeWidth = 10.dp,
            )
        }
    }

    @Composable
    private fun ErrorUi(modifier: Modifier, message: String?) {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_error),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    modifier = modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
                    text = message ?: getString(R.string.error_text),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}