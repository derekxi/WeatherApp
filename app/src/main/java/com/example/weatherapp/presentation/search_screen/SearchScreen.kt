package com.example.weatherapp.presentation.search_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.presentation.FullScreenMode

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(){
    val viewModel = viewModel<SearchViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val cities by viewModel.city.collectAsState() //für Suchvorschläge relevant
    val isSearching by viewModel.isSearching.collectAsState()
    androidx.compose.material.Scaffold(
        scaffoldState = rememberScaffoldState(),
        modifier = Modifier
            .padding(bottom = 60.dp)
    ) {
        FullScreenMode()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Search")}
           )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
//            if (isSearching) {
//                Box(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    CircularProgressIndicator(
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//            } else {
//                LazyColumn(modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//                ) {
//                    items(cities as Int){ city ->
//                        Text(
//                            text = "$city",
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 16.dp)
//                        )
//                    }
//                }
//
//            }
        }
    }
}