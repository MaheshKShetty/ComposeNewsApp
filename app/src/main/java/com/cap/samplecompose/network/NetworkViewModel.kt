package com.cap.samplecompose.network

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cap.samplecompose.model.ArticlesItem
import com.cap.samplecompose.model.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(private val repo:NetworkRepo) : ViewModel() {

    private var _newsList: MutableStateFlow<List<ArticlesItem?>?> = MutableStateFlow(null)
    val newsListResponse = _newsList.asStateFlow()

    fun getNewList()  {
        viewModelScope.launch(Dispatchers.Main) {
            repo.getNewsList()
                .catch { e ->
                }
                .collect {
                    _newsList.value = it.articles
                }
        }
    }
}