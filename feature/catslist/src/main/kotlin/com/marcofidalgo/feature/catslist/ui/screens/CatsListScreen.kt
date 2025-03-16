package com.marcofidalgo.feature.catslist.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CatsListScreen(
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = "Hello CatsList")
}

@Preview(showBackground = true)
@Composable
private fun PreviewFavouritesScreen() {
    MaterialTheme()
    {
        CatsListScreen()
    }
}
