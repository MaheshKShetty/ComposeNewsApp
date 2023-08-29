package com.cap.samplecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
                val navController: NavHostController = rememberNavController()
                Navigation(networkViewModel, navController = navController)
                navController.navigate(NewsScreenRoute.route)
            }
        }
    }
}