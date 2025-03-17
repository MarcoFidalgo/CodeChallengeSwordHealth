package com.marcofidalgo.feature.catslist.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.marcofidalgo.feature.catslist.viewmodel.CatBreedsViewModel

@Composable
fun CatsListScreen(
    viewModel: CatBreedsViewModel = hiltViewModel()
) {
    Text(modifier = Modifier, text = "Hello CatsList")
    val favorites = viewModel.catBreedsList.collectAsState(initial = listOf())
}

@Preview(showBackground = true)
@Composable
private fun PreviewFavouritesScreen() {
    MaterialTheme()
    {
        CatsListScreen()
    }
}
