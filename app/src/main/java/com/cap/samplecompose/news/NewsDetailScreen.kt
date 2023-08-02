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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cap.samplecompose.helper.AppToolBar
import com.cap.samplecompose.helper.ShareClick
import com.cap.samplecompose.helper.Utils.modifier
import com.cap.samplecompose.helper.getDateInRequiredFormat
import com.cap.samplecompose.helper.onBackClickListner
import com.cap.samplecompose.helper.onShareClickListner
import com.cap.samplecompose.model.ArticlesItem
import com.cap.samplecompose.network.NetworkViewModel
import javax.inject.Inject

@Composable
fun NewsDetailScreen(viewModel: NetworkViewModel, navController: NavController) {
    val context = LocalContext.current
    val item = viewModel.selectedArticle
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
    Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)) {
        Column {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item?.urlToImage),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.height(300.dp)
                        .fillMaxWidth()
                )
            }
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = getDateInRequiredFormat(item?.publishedAt ?: ""),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = modifier,
                text = item?.title ?: "",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = item?.description ?: "",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = item?.content ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Source : " + item?.source?.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
