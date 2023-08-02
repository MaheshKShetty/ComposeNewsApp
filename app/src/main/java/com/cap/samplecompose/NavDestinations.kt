
package com.cap.samplecompose

import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.cap.samplecompose.model.ArticlesItem

interface NavDestination {
    val route: String
}

object NewsDetailScreenRoute : NavDestination {
    override val route = "newsDetail"
}

private fun NavHostController.navigateToSingleNewsDetail(accountType: ArticlesItem,modifer:Modifier) {
    this.navigate(NewsDetailScreenRoute.route)
}


