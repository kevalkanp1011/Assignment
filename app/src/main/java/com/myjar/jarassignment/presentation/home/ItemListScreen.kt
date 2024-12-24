package com.myjar.jarassignment.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.myjar.jarassignment.presentation.components.ItemCard


@Composable
fun ItemListScreen(
    viewModel: JarViewModel,
    onNavigateToDetail: (String) -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    var searchQuery by rememberSaveable { mutableStateOf("") }
    val searchItems = viewModel.searchItemList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQuery,
            onValueChange = {
                searchQuery = it.trim()
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if(searchQuery.isBlank() || searchQuery == " ") {
                            viewModel.resetSearch()
                        } else {
                            viewModel.search(searchQuery)
                        }

                    }
                ) {
                    Icon(Icons.Default.Search, null)
                }
            }
        )

        if (searchItems.value.isEmpty()) {
            Text("No results are available")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(searchItems.value, key = { it.id }) { item ->
                ItemCard(
                    item = item,
                    onClick = { onNavigateToDetail("item_detail/${item.id}") }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }


    }



}