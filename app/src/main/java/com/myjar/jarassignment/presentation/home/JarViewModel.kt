package com.myjar.jarassignment.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myjar.jarassignment.createRetrofit
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.data.repository.JarRepositoryImpl
import com.myjar.jarassignment.domain.repository.JarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JarViewModel: ViewModel() {

    private val _listStringData = MutableStateFlow<List<ComputerItem>>(emptyList())

    private val _searchItemList: MutableStateFlow<List<ComputerItem>> = _listStringData
    val searchItemList: StateFlow<List<ComputerItem>> = _searchItemList.asStateFlow()


    private val repository: JarRepository = JarRepositoryImpl(createRetrofit())


    fun fetchData() {
        viewModelScope.launch {
            val flow = repository.fetchResults()
            flow.collect { listData ->
                _listStringData.update {
                    listData
                }
            }

        }
    }

    fun search(query: String) {
        _searchItemList.update {
            searchItemList.value.filter {
                it.name == query
            }
        }
    }

    fun resetSearch() {
        viewModelScope.launch {
            _listStringData.collect { listData ->
                _searchItemList.update {
                    listData
                }
            }
        }

    }
}