package com.cap.samplecompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cap.samplecompose.network.NetworkViewModel
import com.cap.samplecompose.news.NewsDetailScreen
import com.cap.samplecompose.news.NewsScreen

@Composable
fun Navigation(viewModel: NetworkViewModel, navController: NavHostController) {
    NavHost(navController, startDestination = NewsScreenRoute.route) {
        composable(route = NewsScreenRoute.route) {
            NewsScreen(navController = navController, article = viewModel.networkList)
        }
        composable(route = NewsDetailScreenRoute.route) {
            NewsDetailScreen(item = viewModel.networkList?.get(0),navController = navController)
        }
    }
}