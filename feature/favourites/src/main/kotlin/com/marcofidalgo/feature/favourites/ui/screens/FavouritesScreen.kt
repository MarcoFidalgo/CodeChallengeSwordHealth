package com.marcofidalgo.feature.favourites.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcofidalgo.favourites.R

@Composable
fun FavouritesScreen() {
    EmptyFavouritesList()
}

@Composable
fun EmptyFavouritesList() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            painter = painterResource(R.drawable.empty_favourites_fallback),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            text = stringResource(R.string.empty_favourites)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFavouritesScreen() {
    MaterialTheme() {
        FavouritesScreen()
    }
}
