package com.cap.samplecompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cap.samplecompose.network.NetworkViewModel
import com.cap.samplecompose.news.BottomSheet
import com.cap.samplecompose.news.NewsDetailScreen
import com.cap.samplecompose.news.NewsScreen

@Composable
fun Navigation(viewModel: NetworkViewModel, navController: NavHostController) {
    NavHost(navController, startDestination = NewsScreenRoute.route) {
        composable(route = NewsScreenRoute.route) {
            NewsScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = NewsDetailScreenRoute.route) {
            NewsDetailScreen(viewModel = viewModel,navController = navController)
        }

        composable(route = NewsFilterScreenRoute.route) {
            BottomSheet(onDismiss = {

            })
        }
    }
}