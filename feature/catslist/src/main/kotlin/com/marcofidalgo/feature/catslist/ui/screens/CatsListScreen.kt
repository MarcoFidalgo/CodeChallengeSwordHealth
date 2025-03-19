package com.marcofidalgo.feature.catslist.ui.screens

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.marcofidalgo.designsystem.R
import com.marcofidalgo.designsystem.utils.ImageHelper
import com.marcofidalgo.feature.catslist.data.remote.CatBreed
import com.marcofidalgo.feature.catslist.viewmodel.CatBreedsViewModel
import com.marcofidalgo.catslist.R as LR

@Composable
fun CatsListScreen(
    viewModel: CatBreedsViewModel = hiltViewModel(),
    onCatClick: (CatBreed?) -> Unit
) {

    val catBreedsState = viewModel.catBreeds.collectAsState(initial = listOf())
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Header()
        BreedSearchBar()
        when {
            isLoading -> LoadingIndicator()
            errorMessage != null -> ErrorMessage(errorMessage!!)
            else -> CatsGrid(catBreedsState.value, onCatClick)
        }
    }
}

@Composable
fun Header() {
    Text(
        modifier = Modifier,
        text = "Cats App",
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun BreedSearchBar() {
    val viewModel = hiltViewModel<CatBreedsViewModel>()
    Column(modifier = Modifier.padding(10.dp)) {
        var input by remember { mutableStateOf("") }
        TextField(
            value = input,
            readOnly = false,
            onValueChange = { query ->
                input = query
                viewModel.searchCatBreedDebounced(query)
            },
            label = { Text(stringResource(LR.string.search_label)) },
            modifier = Modifier
                .pointerInput(Unit) {
                    awaitEachGesture {
                        awaitFirstDown(pass = PointerEventPass.Initial)
                    }
                }
                .padding(vertical = 32.dp)
        )
    }
}

@Composable
fun CatsGrid(
    catBreeds: List<CatBreed>, onCatClick: (CatBreed?) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = {
            items(catBreeds) { cat ->
                CatItem(cat, onCatClick)
            }
        }
    )
}

@Composable
fun CatItem(catBreed: CatBreed, onCatClick: (CatBreed?) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onCatClick(catBreed) }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.LightGray)

            ) {
                CatImage(catBreedId = catBreed.reference_image_id)
                IconButton(
                    onClick = {}//TODO fav lambda
                ) { }
            }
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    text = catBreed.name ?: "Cat Name",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    lineHeight = (18).sp
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CatImage(catBreedId: String?) {
    GlideImage(
        model = catBreedId?.let {
            val catImageUrl = ImageHelper.getCatImage(breedId = it)
            Log.d("CATS_::", "CAT image URL: $catImageUrl")
            catImageUrl
        },
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth(),
        failure = placeholder(R.drawable.image_error_fallback),
    ) { builder ->
        builder.listener(
            object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean,
                ): Boolean = false

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }
            },
        )
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Text(
        text = message,
        color = Color.Red,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewFavouritesScreen() {
    MaterialTheme() {
        CatsListScreen(onCatClick = {})
    }
}

@Preview
@Composable
private fun ErrorMessagePreview() {
    ErrorMessage("Error Example!")
}
