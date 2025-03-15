package com.marcofidalgo.feature.home.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = "Hello HOME")
}

@Preview(showBackground = true)
@Composable
private fun PreviewRadioHomeScreen() {
    MaterialTheme()
    {
        HomeScreen()
    }
}
