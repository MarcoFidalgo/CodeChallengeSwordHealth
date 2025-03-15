package com.marcofidalgo.feature.favourites.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = "Hello Favourites")
}

@Preview(showBackground = true)
@Composable
private fun PreviewFavouritesScreen() {
    MaterialTheme()
    {
        FavouritesScreen()
    }
}
