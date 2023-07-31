package com.cap.samplecompose.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cap.samplecompose.model.ArticlesItem
import com.cap.samplecompose.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(private val repo:NetworkRepo) : ViewModel() {

    private var _newsList: MutableStateFlow<Resource<List<ArticlesItem?>?>> = MutableStateFlow(Resource.Loading())
    val newsListResponse = _newsList.asStateFlow()

    fun getNewList()  {
        viewModelScope.launch(Dispatchers.Main) {
            _newsList.value = Resource.Loading()
            repo.getNewsList()
                .catch { e ->
                    _newsList.value = Resource.Error(e.message)
                }
                .collect {
                    _newsList.value = Resource.Success(it.articles)
                }
        }
    }
}