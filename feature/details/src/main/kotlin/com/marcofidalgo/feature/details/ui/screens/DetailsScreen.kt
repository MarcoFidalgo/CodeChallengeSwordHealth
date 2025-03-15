package com.marcofidalgo.feature.details.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = "Hello Details")
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetailsScreen() {
    MaterialTheme()
    {
        HomeScreen()
    }
}
